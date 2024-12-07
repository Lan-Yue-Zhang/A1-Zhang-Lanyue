const { Builder, By, until } = require('selenium-webdriver');


async function A1_scenario() {
    let driver = await new Builder().forBrowser('chrome').build();
    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.xpath("//button[contains(text(), 'A1_scenario')]"));
        await startButton.click();

        await driver.wait(until.elementTextContains(driver.findElement(By.id('game-status')), 'Game started'), 5000);
        console.log("Game started successfully.");
        // Start game
        let handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.log("A1 initial hand : " + handStatus);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(5) F(15) F(15) D(5) S(10) S(10) H(10) H(10) B(15) B(15) L(20)"),"error at initial P1 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(5) F(15) F(15) F(40) D(5) S(10) H(10) H(10) B(15) B(15) E(30)"),"error at initial P2 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(5) F(15) D(5) S(10) S(10) S(10) H(10) H(10) B(15) L(20)"),"error at initial P3 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(5) F(15) F(15) F(40) D(5) D(5) S(10) H(10) H(10) B(15) L(20) E(30)"),"error at initial P4 hand");
        let shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.log("A1 initial shield : " + shieldStatus);
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:0    P3 shields:0    P4 shields:0"),"error at initial shield");
        let numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.log("A1 initial cards number : " + numStatus);
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at initial cards number");

        //P1 draws a quest of 4 stages
        let drawButton = await driver.findElement(By.xpath("//button[contains(text(), 'Draw Event card')]"));
        await drawButton.click();
        await driver.sleep(1000);
        let gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The current player has drawn an 4 stage") ,"error at drawn an 4 stage");

        //P1 is asked but declines to sponsor
        let noButton = await driver.findElement(By.xpath("//button[contains(text(), 'No')]"));
        await noButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("P1 is not sponsor") ,"error at P1 is asked but declines to sponsor");
        let leaveButton = await driver.findElement(By.xpath("//button[contains(text(), 'Leave')]"));
        await leaveButton.click();
        await driver.sleep(1000);

        //P2 is asked and sponsors and then builds the 4 stages
        let yesButton = await driver.findElement(By.xpath("//button[contains(text(), 'Yes')]"));
        await yesButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("P2 is sponsor") ,"error at P2 is asked and sponsors");
        let playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");

        let cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        let quitButton = await driver.findElement(By.xpath("//button[contains(text(), 'Quit')]"));
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 10, "error at player should have 10 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 1 is 15 : F(5) H(10)") ,"error at drawn cards in stage 1");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 8, "error at player should have 8 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 2 is 25 : F(15) S(10)") ,"error at drawn cards in stage 2");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 5, "error at player should have 5 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 3 is 35 : F(15) D(5) B(15)") ,"error at drawn cards in stage 3");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(40)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 3, "error at player should have 3 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 4 is 55 : F(40) B(15)") ,"error at drawn cards in stage 3");

        await leaveButton.click();
        await driver.sleep(1000);

        //p1
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(5) F(15) F(15) F(30) D(5) S(10) S(10) H(10) H(10) B(15) B(15) L(20)"),"error at P1 is asked and decides to participate – draws an F30");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:13    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(15) F(15) F(30) D(5) S(10) S(10) H(10) H(10) B(15) B(15) L(20)"),"error at P1 discards an F5");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number");
        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(5) F(15) D(5) S(10) S(10) S(10) S(10) H(10) H(10) B(15) L(20)"),"error at P3 is asked and decides to participate – draws a Sword");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:13    P4 cards:12") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(15) D(5) S(10) S(10) S(10) S(10) H(10) H(10) B(15) L(20)"),"error at P3 discards an F5");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number");
        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(5) F(15) F(15) F(40) D(5) D(5) S(10) H(10) H(10) B(15) B(15) L(20) E(30)"),"error at P3 is asked and decides to participate – draws a Sword");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:13") ,"error at cards number");
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(15) F(15) F(40) D(5) D(5) S(10) H(10) H(10) B(15) B(15) L(20) E(30)"),"error at P4 discards an F5");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number");
        await driver.sleep(1000);
        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 1:
        //p1
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 1 is 15 : D(5) S(10) current stage win") ,"error at drawn cards in stage 1");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 10, "error at player should have 10 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:10    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number P1 in stage 1");

        await leaveButton.click();
        await driver.sleep(1000);

        //p3
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 1 is 15 : S(10) D(5) current stage win") ,"error at drawn cards in stage 1");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 10, "error at player should have 10 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:10    P2 cards:12    P3 cards:10    P4 cards:12") ,"error at cards number P3 in stage 1");

        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 1 is 15 : D(5) H(10) current stage win") ,"error at drawn cards in stage 1");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 10, "error at player should have 10 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:10    P2 cards:12    P3 cards:10    P4 cards:10") ,"error at cards number P4 in stage 1");

        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 2:
        //p1
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(10) F(15) F(15) F(30) S(10) H(10) H(10) B(15) B(15) L(20)"),"error at P1 draws a F10");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:11    P2 cards:12    P3 cards:10    P4 cards:10") ,"error at cards number P1 draws a F10");

        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(15) S(10) S(10) S(10) H(10) H(10) B(15) L(20) L(20)"),"error at P3 draws a Lance");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:11    P2 cards:12    P3 cards:11    P4 cards:10") ,"error at cards number P3 draws a Lance");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(15) F(15) F(40) D(5) S(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P4 draws a Lance");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:11    P2 cards:12    P3 cards:11    P4 cards:11") ,"error at cards number P4 draws a Lance");

        await leaveButton.click();
        await driver.sleep(1000);
        //p1
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 2 is 20 : H(10) S(10) current stage fail") ,"error at P1 loses and cannot go to the next stage");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:11    P4 cards:11") ,"error at cards number P1 in stage 2");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(10) F(15) F(15) F(30) H(10) B(15) B(15) L(20)"),"error at final P1 hand");

        await leaveButton.click();
        await driver.sleep(1000);

        //p3
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 2 is 25 : B(15) S(10) current stage win") ,"error at P3 sees their hand and builds an attack: Axe + Sword");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:9    P4 cards:11") ,"error at cards number P3 in stage 2");

        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 2 is 25 : H(10) B(15) current stage win") ,"error at P4 sees their hand and builds an attack: Horse + Axe");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:9    P4 cards:9") ,"error at cards number P4 in stage 2");

        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 3:
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(15) S(10) S(10) H(10) H(10) B(15) L(20) L(20)"),"error at P3 draws an Axe");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:10    P4 cards:9") ,"error at cards number P3 draws an Axe");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(15) F(15) F(40) D(5) S(10) S(10) B(15) L(20) L(20) E(30)"),"error at P4 draws a Sword");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:10    P4 cards:10 ") ,"error at cards number P4 draws a Sword");

        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'L(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 3 is 40 : L(20) H(10) S(10) current stage win") ,"error at P3 sees their hand and builds an attack: Lance + Horse + Sword");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 7, "error at player should have 7 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:7    P4 cards:10") ,"error at cards number P3 in stage 3");

        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'L(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 3 is 45 : B(15) S(10) L(20) current stage win") ,"error at P4 sees their hand and builds an attack: Axe + Sword + Lance =");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 7, "error at player should have 7 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:7    P4 cards:7") ,"error at cards number P4 in stage 3");

        await leaveButton.click();
        await driver.sleep(1000);
        // Stage 4:
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(15) F(30) S(10) H(10) B(15) L(20)"),"error at P3 draws a F30");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:8    P4 cards:7") ,"error at cards number P3 draws a F30");
        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(15) F(15) F(40) D(5) S(10) L(20) L(20) E(30)"),"error at P4 draws a Lance");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:8    P4 cards:8") ,"error at cards number P4 draws a Lance");
        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'L(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 4 is 45 : B(15) H(10) L(20) current stage fail") ,"error at P3 sees their hand and builds an attack: Axe + Horse + Lance");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 5, "error at player should have 5 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:5    P4 cards:8") ,"error at cards number p3");
        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'L(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'E(30)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 4 is 65 : D(5) S(10) L(20) E(30) current stage win") ,"error at P4 sees their hand and builds an attack: Dagger + Sword + Lance + Excalibur");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 4, "error at player should have 4 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:3    P3 cards:5    P4 cards:4") ,"error at cards number p4");
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:0    P3 shields:0    P4 shields:4"),"error at final shield");
        await leaveButton.click();
        await driver.sleep(1000);

        //p2
        for (let i = 0; i < 4; i++) {
            cardButton = await driver.findElement(By.className('card'));;
            await cardButton.click();
            await driver.sleep(1000);
        }
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.log("A1 final hand : " + handStatus);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(10) F(15) F(15) F(30) H(10) B(15) B(15) L(20)"),"error at final P1 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: S(10) S(10) S(10) S(10) S(10) S(10) H(10) H(10) H(10) H(10) H(10) E(30)"),"error at final P2 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(15) F(30) S(10)"),"error at final P3 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(15) F(15) F(40) L(20)"),"error at final P4 hand");
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.log("A1 final shield : " + shieldStatus);
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:0    P3 shields:0    P4 shields:4"),"error at final shield");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.log("A1 final cards number : " + numStatus);
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:9    P2 cards:12    P3 cards:5    P4 cards:4") ,"error at final cards number");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("No winner of game, continues the game") ,"error at No winner of game, continues the game");
        await driver.sleep(3000);
    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.quit();
    }
}

A1_scenario();




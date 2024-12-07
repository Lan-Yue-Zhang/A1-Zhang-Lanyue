const { Builder, By, until } = require('selenium-webdriver');

async function winner1_game_with_events() {
    let driver = await new Builder().forBrowser('chrome').build();
    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.xpath("//button[contains(text(), '1winner_game_with_events')]"));
        await startButton.click();

        await driver.wait(until.elementTextContains(driver.findElement(By.id('game-status')), 'Game started'), 1000);
        console.log("Game started successfully.");
        // Start game
        let handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.log("winner1 initial hand : " + handStatus);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(5) F(10) F(10) F(15) F(15) F(20) F(20) D(5) D(5) D(5) D(5)"),"error at initial P1 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(25) F(30) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at initial P2 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(25) F(30) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at initial P3 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(25) F(30) F(70) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20)"),"error at initial P4 hand");

        let shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.log("winner1 initial shield : " + shieldStatus);
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:0    P3 shields:0    P4 shields:0"),"error at initial shield");
        let numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.log("winner1 initial cards number : " + numStatus);
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at initial cards number");

        //P1 draws a quest of 4 stages
        let drawButton = await driver.findElement(By.xpath("//button[contains(text(), 'Draw Event card')]"));
        await drawButton.click();
        await driver.sleep(1000);
        let gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The current player has drawn an 4 stage") ,"error at drawn an 4 stage");

        //P1 is asked and sponsors and then builds the 4 stages
        let yesButton = await driver.findElement(By.xpath("//button[contains(text(), 'Yes')]"));
        await yesButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("P1 is sponsor") ,"error at P1 is asked and sponsors");
        let playerCards = await driver.findElements(By.css("#player-cards .card"));
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");

        let cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        let quitButton = await driver.findElement(By.xpath("//button[contains(text(), 'Quit')]"));
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 1 is 5 : F(5)") ,"error at drawn cards in stage 1");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 10, "error at player should have 10 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 2 is 10 : F(10)") ,"error at drawn cards in stage 2");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 3 is 15 : F(15)") ,"error at drawn cards in stage 3");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 8, "error at player should have 8 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 4 is 20 : F(20) ") ,"error at drawn cards in stage 4");

        let leaveButton = await driver.findElement(By.xpath("//button[contains(text(), 'Leave')]"));
        await leaveButton.click();
        await driver.sleep(1000);

        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(25) F(30) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P2 draws F5");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:13    P3 cards:12    P4 cards:12") ,"error at cards number P2 draws F5");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(25) F(30) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P2 discards an F5");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number discards an F5");
        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(10) F(25) F(30) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P3 draws F10");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:13    P4 cards:12") ,"error at cards number P3 draws F10");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(25) F(30) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P3 discards F10");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number P3 discards F10");
        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(20) F(25) F(30) F(70) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20)"),"error at P4 draws F20");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:13") ,"error at cards number P4 draws F20");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(25) F(30) F(70) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20)"),"error at P4 discards F20");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number P4 discards F20");
        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 1:
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 1 is 10 : S(10) current stage win") ,"error at P2, P3, P4 all use the same attack, a sword. All win and have 11 cards.");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number P2 in stage 1");
        await leaveButton.click();
        await driver.sleep(1000);

        //p3
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 1 is 10 : S(10) current stage win") ,"error at P2, P3, P4 all use the same attack, a sword. All win and have 11 cards.");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:12") ,"error at cards number P3 in stage 1");
        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 1 is 10 : S(10) current stage win") ,"error at P2, P3, P4 all use the same attack, a sword. All win and have 11 cards.");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:11") ,"error at cards number P4 in stage 1");
        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 2:
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(15) F(25) F(30) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P2 draws F15");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:11    P4 cards:11") ,"error at cards number P2 draws F15");
        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(25) F(30) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P3 draws F5");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:11") ,"error at cards number P3 draws F5");
        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(25) F(25) F(30) F(70) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20)"),"error at P4 draws F25");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number P4 draws F25");
        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 2 is 10 : H(10) current stage win") ,"error at P2, P3, P4 all use the same attack, a horse. All win and have 11 cards.");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number P2 in stage 2");

        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 2 is 10 : H(10) current stage win") ,"error at P2, P3, P4 all use the same attack, a horse. All win and have 11 cards.");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:12") ,"error at cards number P3 in stage 2");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 2 is 10 : H(10) current stage win") ,"error at P2, P3, P4 all use the same attack, a horse. All win and have 11 cards.");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:11") ,"error at cards number P4 in stage 2");

        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 3:
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(15) F(25) F(30) S(10) S(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P2 draws F5");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:11    P4 cards:11") ,"error at cards number P2 draws F5");

        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(10) F(25) F(30) S(10) S(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P3 draws F10");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:11") ,"error at cards number P3 draws F10");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(20) F(25) F(25) F(30) F(70) S(10) S(10) H(10) B(15) B(15) L(20) L(20)"),"error at P4 draws F20");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 3 is 15 : B(15) current stage win") ,"error at P2, P3, P4 all use the same attack, an axe. All win and have 11 cards.");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 3 is 15 : B(15) current stage win") ,"error at P2, P3, P4 all use the same attack, an axe. All win and have 11 cards.");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 3 is 15 : B(15) current stage win") ,"error at P2, P3, P4 all use the same attack, an axe. All win and have 11 cards.");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:11") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        // Stage 4:
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(5) F(15) F(25) F(30) S(10) S(10) H(10) B(15) L(20) L(20) E(30)"),"error at P2 draws F5");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:11    P4 cards:11") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(10) F(10) F(25) F(30) S(10) S(10) H(10) B(15) L(20) L(20) E(30)"),"error at P3 draws F10");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:11") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(20) F(20) F(25) F(25) F(30) F(70) S(10) S(10) H(10) B(15) L(20) L(20)"),"error at P4 draws F20");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'L(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 4 is 20 : L(20) current stage win") ,"error at P2, P3, P4 all use the same attack, a lance. All win and have 11 cards");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'L(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 4 is 20 : L(20) current stage win") ,"error at P2, P3, P4 all use the same attack, a lance. All win and have 11 cards");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'L(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 4 is 20 : L(20) current stage win") ,"error at P2, P3, P4 all use the same attack, a lance. All win and have 11 cards");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:8    P2 cards:11    P3 cards:11    P4 cards:11") ,"error at cards number");
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:4    P3 shields:4    P4 shields:4"),"error at final shield");
        await leaveButton.click();
        await driver.sleep(1000);

        //p1
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(5) F(5) F(10) F(10) F(10) F(15) F(15) F(15) F(15) F(15) F(20) D(5) D(5) D(5) D(5)"),"error at P1 discards 4 cards used in quest and draws 8 cards: 2xF5, 2xF10, 4xF15");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 16, "error at player should have 16 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:16    P2 cards:11    P3 cards:11    P4 cards:11") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(10) F(15) F(15) F(15) F(15) F(15) F(20) D(5) D(5) D(5) D(5)"),"error at P1 discards 2xF5, 2xF10 and has 12 cards.");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:11") ,"error at cards number");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("No winner of game, continues the game") ,"error at No winner of game, continues the game");
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:4    P3 shields:4    P4 shields:4"),"error at final shield");

        await leaveButton.click();
        await driver.sleep(1000);

        //P2 draws ‘Plague’ and loses 2 shields
        drawButton = await driver.findElement(By.xpath("//button[contains(text(), 'Draw Event card')]"));
        await drawButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The current player has drawn an Plague card The player 2 draws this card immediately loses 2 shields ") ,"error at P2 draws ‘Plague’");
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:2    P3 shields:4    P4 shields:4"),"error at P2 loses 2 shields");
        await leaveButton.click();
        await driver.sleep(1000);

        //P3 draws ‘Prosperity’: All 4 players receive 2 adventure cards
        drawButton = await driver.findElement(By.xpath("//button[contains(text(), 'Draw Event card')]"));
        await drawButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The current player has drawn an Prosperity card") ,"error at P3 draws ‘Prosperity’");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:14    P2 cards:13    P3 cards:13    P4 cards:13") ,"error at cards number");
        await leaveButton.click();
        await driver.sleep(1000);
        //p1
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(10) F(15) F(15) F(15) F(15) F(15) F(20) F(25) F(25) D(5) D(5) D(5) D(5)"),"error at P1 draws 2 cards: 2xF25");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 14, "error at player should have 14 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:14    P2 cards:13    P3 cards:13    P4 cards:13") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(15) F(15) F(15) F(15) F(15) F(20) F(25) F(25) D(5) D(5) D(5) D(5)"),"error at P1 discards 1xF5, 1xF10");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:13    P3 cards:13    P4 cards:13") ,"error at cards number");
        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(5) F(15) F(25) F(30) S(10) S(10) S(10) H(10) H(10) B(15) L(20) E(30)"),"error at P2 draws 2 cards: horse, sword");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:13    P3 cards:13    P4 cards:13") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(15) F(25) F(30) S(10) S(10) S(10) H(10) H(10) B(15) L(20) E(30)"),"error at P2 discards an F5");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:13    P4 cards:13") ,"error at cards number");
        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(10) F(10) F(25) F(30) F(40) S(10) S(10) H(10) B(15) B(15) L(20) E(30)"),"error at P3 draws 2 cards: 1 axe, 1xF40");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:13    P4 cards:13") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(10) F(10) F(25) F(30) F(40) S(10) S(10) H(10) B(15) B(15) L(20) E(30)"),"error at P3 discards F5");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:13") ,"error at cards number");
        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(20) F(20) F(25) F(25) F(30) F(70) D(5) D(5) S(10) S(10) H(10) B(15) L(20)"),"error at P4 draws 2 cards: 2 daggers");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:13") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(20) F(25) F(25) F(30) F(70) D(5) D(5) S(10) S(10) H(10) B(15) L(20)"),"error at P4 discards F20");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number");
        await leaveButton.click();
        await driver.sleep(1000);

        //P4 draws ‘Queen’s favor’ :
        drawButton = await driver.findElement(By.xpath("//button[contains(text(), 'Draw Event card')]"));
        await drawButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The current player has drawn an Queen’s favor card") ,"error at P4 draws ‘Queen’s favor’");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:14") ,"error at cards number");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(20) F(25) F(25) F(25) F(30) F(30) F(70) D(5) D(5) S(10) S(10) H(10) B(15) L(20)"),"error at P4 Draws 1xF30, 1xF25");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 14, "error at player should have 14 cards");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(25)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(30)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(20) F(25) F(25) F(30) F(70) D(5) D(5) S(10) S(10) H(10) B(15) L(20)"),"error at P4 discards F25 and F30");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number");
        await leaveButton.click();
        await driver.sleep(1000);

        //P1 draws a quest of 3 stages
        drawButton = await driver.findElement(By.xpath("//button[contains(text(), 'Draw Event card')]"));
        await drawButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The current player has drawn an 3 stage") ,"error at drawn an 3 stage");

        //P1 is asked and sponsors and then builds the 3 stages
        yesButton = await driver.findElement(By.xpath("//button[contains(text(), 'Yes')]"));
        await yesButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("P1 is sponsor") ,"error at P3 is asked and sponsors");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        quitButton = await driver.findElement(By.xpath("//button[contains(text(), 'Quit')]"));
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 1 is 15 : F(15)") ,"error at drawn cards in stage 1");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 2 is 20 : F(15) D(5)") ,"error at drawn cards in stage 2");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 7, "error at player should have 7 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 3 is 25 : F(20) D(5)") ,"error at drawn cards in stage 3");

        await leaveButton.click();
        await driver.sleep(1000);

        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(15) F(25) F(30) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) E(30)"),"error at P2 draws axe");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:13    P3 cards:12    P4 cards:12") ,"error at cards number P2 draws axe");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(15) F(25) F(30) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) E(30)"),"error at P2 discards an F5");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number P2 discards an F5");
        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(10) F(10) F(25) F(30) F(40) S(10) S(10) H(10) H(10) B(15) B(15) L(20) E(30)"),"error at P3 draws horse");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:13    P4 cards:12") ,"error at cards number P3 draws horse");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(10) F(25) F(30) F(40) S(10) S(10) H(10) H(10) B(15) B(15) L(20) E(30)"),"error at P3 discards F10");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number P3 discards F10");
        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(20) F(25) F(25) F(30) F(50) F(70) D(5) D(5) S(10) S(10) H(10) B(15) L(20)"),"error at P4 draws F50");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:13") ,"error at cards number P4 draws F50");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(25) F(25) F(30) F(50) F(70) D(5) D(5) S(10) S(10) H(10) B(15) L(20)"),"error at P4 discards F20");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number P4 discards F20");
        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 1:
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 1 is 15 : B(15) current stage win") ,"error at P2 attack: axe thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number P2 in stage 1");
        await leaveButton.click();
        await driver.sleep(1000);

        //p3
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 1 is 15 : B(15) current stage win") ,"error at P3 attack: axe thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:12") ,"error at cards number P3 in stage 1");
        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 1 is 10 : H(10) current stage fail") ,"error at P4 attack: horse thus loses (has 11 cards)");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:11") ,"error at cards number P4 in stage 1");
        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 2:
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(15) F(25) F(30) S(10) S(10) S(10) S(10) H(10) H(10) B(15) L(20) E(30)"),"error at P2 draws: sword");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:11    P4 cards:11") ,"error at cards number P2 draws: sword");
        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(10) F(25) F(30) F(40) S(10) S(10) S(10) H(10) H(10) B(15) L(20) E(30)"),"error at P3 draws: sword");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:11") ,"error at cards number P3 draws: sword");
        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 2 is 25 : B(15) H(10) current stage win") ,"error at P2 attack: axe+horse thus wins (has 10 cards)");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 10, "error at player should have 10 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:10    P3 cards:12    P4 cards:11") ,"error at cards number P2 in stage 2  P2 attack: axe+horse");

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
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 2 is 25 : B(15) S(10) current stage win") ,"error at P3 attack: axe+sword thus wins (has 10 cards)");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 10, "error at player should have 10 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:10    P3 cards:10    P4 cards:11") ,"error at cards number P3 in stage 2 P3 attack: axe+sword");

        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 3:
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(15) F(25) F(30) F(40) S(10) S(10) S(10) S(10) H(10) L(20) E(30)"),"error at P2 draws: F40");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:10    P4 cards:11 ") ,"error at cards number P2 draws: F40");

        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(10) F(25) F(30) F(40) F(50) S(10) S(10) H(10) H(10) L(20) E(30)"),"error at P3 draws: F50");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:11    P4 cards:11 ") ,"error at cards number P3 draws: F50");

        await leaveButton.click();
        await driver.sleep(1000);

        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'L(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 3 is 30 : L(20) S(10) current stage win") ,"error at P2 attack: lance + sword thus wins (has 9 cards)");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:11    P4 cards:11") ,"error at cards number P2 in stage 3");

        await leaveButton.click();
        await driver.sleep(1000);
        //p3
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'E(30)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 3 is 30 : E(30) current stage win") ,"error at P3 attack: excalibur thus wins (has 10 cards)");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 10, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:7    P2 cards:9    P3 cards:10    P4 cards:11") ,"error at cards number");
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:5    P3 shields:7    P4 shields:4"),"error at final shield");
        await leaveButton.click();
        await driver.sleep(1000);

        //p1
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(15) F(15) F(15) F(25) F(25) F(35) D(5) D(5) S(10) S(10) S(10) S(10) H(10) H(10) H(10)"),"error at P1 discards 5 quest cards, draws 8 cards: 3 horses, 4 swords, 1 F35 (15 cards)");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 15, "error at player should have 16 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:15    P2 cards:9    P3 cards:10    P4 cards:11") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(25) F(25) F(35) D(5) D(5) S(10) S(10) S(10) S(10) H(10) H(10) H(10)"),"error at P1 discards 3xF15");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");

        console.log("winner2 final hand : " + handStatus);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(25) F(25) F(35) D(5) D(5) S(10) S(10) S(10) S(10) H(10) H(10) H(10)"),"error at final P1 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(15) F(25) F(30) F(40) S(10) S(10) S(10) H(10) E(30)"),"error at final P2 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(10) F(25) F(30) F(40) F(50) S(10) S(10) H(10) H(10) L(20)"),"error at final P3 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(25) F(25) F(30) F(50) F(70) D(5) D(5) S(10) S(10) B(15) L(20)"),"error at final P4 hand");
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.log("winner2 final shield : " + shieldStatus);
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:5    P3 shields:7    P4 shields:4"),"error at final shield");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.log("winner2 final cards number : " + numStatus);
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:10    P4 cards:11") ,"error at final cards number");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The winner is: P3") ,"error at P3 is declared (and asserted as) the winner");

        await driver.sleep(3000);
    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.quit();
    }
}

winner1_game_with_events();




const { Builder, By, until } = require('selenium-webdriver');

async function winner2_game_2winner_quest() {
    let driver = await new Builder().forBrowser('chrome').build();
    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.xpath("//button[contains(text(), '2winner_game_2winner_quest')]"));
        await startButton.click();

        await driver.wait(until.elementTextContains(driver.findElement(By.id('game-status')), 'Game started'), 1000);
        console.log("Game started successfully.");
        // Start game
        let handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.log("winner2 initial hand : " + handStatus);
        console.assert(handStatus.includes("P1: F(5) F(5) F(10) F(10) F(15) F(15) D(5) H(10) H(10) B(15) B(15) L(20)"),"error at initial P1 hand");
        console.assert(handStatus.includes("P2: F(40) F(50) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at initial P2 hand");
        console.assert(handStatus.includes("P3: F(5) F(5) F(5) F(5) D(5) D(5) D(5) H(10) H(10) H(10) H(10) H(10)"),"error at initial P3 hand");
        console.assert(handStatus.includes("P4: F(50) F(70) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at initial P4 hand");

        let shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.log("winner2 initial shield : " + shieldStatus);
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:0    P3 shields:0    P4 shields:0"),"error at initial shield");
        let numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.log("winner2 initial cards number : " + numStatus);
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

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
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
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 2 is 10 : F(5) D(5)") ,"error at drawn cards in stage 2");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 7, "error at player should have 7 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 3 is 20 : F(10) H(10)") ,"error at drawn cards in stage 3");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
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
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 4 is 25 : F(10) B(15)") ,"error at drawn cards in stage 4");
        let leaveButton = await driver.findElement(By.xpath("//button[contains(text(), 'Leave')]"));
        await leaveButton.click();
        await driver.sleep(1000);

        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(40) F(50) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P2 draws F5");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:13    P3 cards:12    P4 cards:12") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(40) F(50) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P2 discards an F5");
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
        console.assert(handStatus.includes("P3: F(5) F(5) F(5) F(5) F(40) D(5) D(5) D(5) H(10) H(10) H(10) H(10) H(10)"),"error at P3 draws F40");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:13    P4 cards:12") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(5) F(40) D(5) D(5) D(5) H(10) H(10) H(10) H(10) H(10)"),"error at P3 discards F5");
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
        console.assert(handStatus.includes("P4: F(10) F(50) F(70) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P4 draws F10");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:13") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(50) F(70) S(10) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P4 discards F10");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number");
        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 1:
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 1 is 10 : H(10) current stage win") ,"error at P2 attack: 1 horse thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);

        //p3
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("empty set of non repeated weapon cards") ,"error at P3 attack: nothing thus loses");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 1 is 10 : H(10) current stage win") ,"error at P4 attack: 1 horse thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:11") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 2:
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(10) F(40) F(50) S(10) S(10) S(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P2 draws F10");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:11") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(30) F(50) F(70) S(10) S(10) S(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P4 draws F30 ");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12 ") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 2 is 10 : S(10) current stage win") ,"error at P2 attack: sword thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 2 is 10 : S(10) current stage win") ,"error at P4 attack: sword thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:11") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 3:
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(10) F(30) F(40) F(50) S(10) S(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P2 draws F30");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:11") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(15) F(30) F(50) F(70) S(10) S(10) H(10) B(15) B(15) L(20) L(20) E(30)"),"error at P4 draws F15");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 3 is 20 : H(10) S(10) current stage win") ,"error at P2 attack: horse + sword thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 10, "error at player should have 10 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:10    P3 cards:12    P4 cards:12") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 3 is 20 : H(10) S(10) current stage win") ,"error at P4 attack: horse + sword, thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 10, "error at player should have 10 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:10    P3 cards:12    P4 cards:10") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        // Stage 4:
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(10) F(15) F(30) F(40) F(50) S(10) B(15) B(15) L(20) L(20) E(30)"),"error at P2 draws F15");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:10") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(15) F(20) F(30) F(50) F(70) S(10) B(15) B(15) L(20) L(20) E(30)"),"error at P4 draws F20");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:11") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 4 is 25 : S(10) B(15) current stage win") ,"error at P2 attack: sword + axe thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:12    P4 cards:11") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 4 is 25 : S(10) B(15) current stage win") ,"error at P4: attack: sword + axe thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:5    P2 cards:9    P3 cards:12    P4 cards:9") ,"error at P1 discards 7 cards used for quest");
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:4    P3 shields:0    P4 shields:4"),"error at final shield");
        await leaveButton.click();
        await driver.sleep(1000);

        //p1
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(10) F(15) F(15) F(15) F(15) F(20) F(20) F(20) F(20) F(25) F(25) F(30) H(10) B(15) L(20)"),"error at P1 draws 11 cards");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 16, "error at player should have 16 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:16    P2 cards:9    P3 cards:12    P4 cards:9") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(15) F(15) F(20) F(20) F(20) F(20) F(25) F(25) F(30) H(10) B(15) L(20)"),"error at P1 discards: 1xF5, 1xF10, 2xF15");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:12    P4 cards:9") ,"error at cards number");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("No winner of game, continues the game") ,"error at No winner of game, continues the game");
        await leaveButton.click();
        await driver.sleep(1000);

        //P2 draws a quest of 3 stages
        drawButton = await driver.findElement(By.xpath("//button[contains(text(), 'Draw Event card')]"));
        await drawButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The current player has drawn an 3 stage") ,"error at drawn an 3 stage");

        //P2 is asked but declines to sponsor
        let noButton = await driver.findElement(By.xpath("//button[contains(text(), 'No')]"));
        await noButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("P2 is not sponsor") ,"error at P2 is asked but declines to sponsor");
        await leaveButton.click();
        await driver.sleep(1000);

        //P3 is asked and sponsors and then builds the 4 stages
        yesButton = await driver.findElement(By.xpath("//button[contains(text(), 'Yes')]"));
        await yesButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("P3 is sponsor") ,"error at P3 is asked and sponsors");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        quitButton = await driver.findElement(By.xpath("//button[contains(text(), 'Quit')]"));
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 1 is 5 : F(5)") ,"error at drawn cards in stage 1");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
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
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 2 is 10 : F(5) D(5)") ,"error at drawn cards in stage 2");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 7, "error at player should have 7 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P3 in stage 3 is 15 : F(5) H(10)") ,"error at drawn cards in stage 3");
        await leaveButton.click();
        await driver.sleep(1000);

        //p1
        await noButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(15) F(15) F(20) F(20) F(20) F(20) F(25) F(25) F(30) H(10) B(15) L(20)"),"error at P1 declines to participate. ");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:12    P4 cards:9") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(10) F(15) F(30) F(40) F(50) D(5) B(15) L(20) L(20) E(30)"),"error at P2 draws dagger ");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:10    P3 cards:12    P4 cards:9") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(15) F(20) F(30) F(50) F(70) D(5) B(15) L(20) L(20) E(30)"),"error at P4 draws dagger");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:10    P3 cards:12    P4 cards:10") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        // Stage 1:
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 1 is 5 : D(5) current stage win") ,"error at P2 attack: dagger thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:12    P4 cards:10") ,"error at cards number P2 in stage 1");

        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 1 is 5 : D(5) current stage win") ,"error at P4 attack: dagger thus wins");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:12    P4 cards:9") ,"error at cards number");

        await leaveButton.click();
        await driver.sleep(1000);
        // Stage 2:
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(10) F(15) F(15) F(30) F(40) F(50) B(15) L(20) L(20) E(30)"),"error at P2 draws 1xF15");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:10    P3 cards:12    P4 cards:9") ,"error at cards number P2 draws 1xF15");

        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(15) F(15) F(20) F(30) F(50) F(70) B(15) L(20) L(20) E(30)"),"error at P4 draws 1xF15");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:10    P3 cards:12    P4 cards:10") ,"error at cards number P4 draws 1xF15");

        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 2 is 15 : B(15) current stage win") ,"error at P2 and P4 attacks are the same: 1 axe; both win");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:12    P4 cards:10") ,"error at cards number P2 in stage 2");

        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 2 is 15 : B(15) current stage win") ,"error at P2 and P4 attacks are the same: 1 axe; both win");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:12    P4 cards:9") ,"error at cards number P4 in stage 2");

        await leaveButton.click();
        await driver.sleep(1000);
        // Stage 3:
        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(10) F(15) F(15) F(25) F(30) F(40) F(50) L(20) L(20) E(30)"),"error at P2 draws 1xF25 ");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:10    P3 cards:12    P4 cards:9") ,"error at cards number P2 draws 1xF25");
        await leaveButton.click();
        await driver.sleep(1000);
        //p4
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(15) F(15) F(20) F(25) F(30) F(50) F(70) L(20) L(20) E(30)"),"error at P4 draws 1xF25 ");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:10    P3 cards:12    P4 cards:10") ,"error at cards number P4 draws 1xF25");
        await leaveButton.click();
        await driver.sleep(1000);
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'E(30)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 3 is 30 : E(30) current stage win") ,"error at P2 and P4 attack are the same: 1 excalibur; both win");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:12    P4 cards:10") ,"error at cards number P2 in stage 3");

        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'E(30)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P4 in stage 3 is 30 : E(30) current stage win") ,"error at P2 and P4 attack are the same: 1 excalibur; both win");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 9, "error at player should have 9 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:7    P4 cards:9") ,"error at cards number P4 in stage 3");
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:7    P3 shields:0    P4 shields:7"),"error at final shield for 3 stage");
        await leaveButton.click();
        await driver.sleep(1000);

        //P3 trims their hand and discards 1xF20, 1xF25, 1xF30
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(20) F(20) F(25) F(30) F(40) D(5) D(5) S(10) H(10) H(10) H(10) H(10) B(15) B(15) L(20)"),"error at P3 draws 8 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:15    P4 cards:9"),"error at cards number P3 trims their hand and discards 1xF20, 1xF25, 1xF30");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(25)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(30)')]"));
        await cardButton.click();
        await driver.sleep(1000);

        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.log("winner2 final hand : " + handStatus);
        console.assert(handStatus.includes("P1: F(15) F(15) F(20) F(20) F(20) F(20) F(25) F(25) F(30) H(10) B(15) L(20)"),"error at final P1 hand");
        console.assert(handStatus.includes("P2: F(10) F(15) F(15) F(25) F(30) F(40) F(50) L(20) L(20)"),"error at final P2 hand");
        console.assert(handStatus.includes("P3: F(20) F(40) D(5) D(5) S(10) H(10) H(10) H(10) H(10) B(15) B(15) L(20)"),"error at final P3 hand");
        console.assert(handStatus.includes("P4: F(15) F(15) F(20) F(25) F(30) F(50) F(70) L(20) L(20)"),"error at final P4 hand");
        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.log("winner2 final shield : " + shieldStatus);
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:7    P3 shields:0    P4 shields:7"),"error at final shield");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.log("winner2 final cards number : " + numStatus);
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:9    P3 cards:12    P4 cards:9") ,"error at final cards number");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The winner is: P2") ,"error at P2 and P4 are declared (and asserted as) winners.");
        console.assert(gameStatus.includes("The winner is: P4") ,"error at P2 and P4 are declared (and asserted as) winners.");
        await driver.sleep(3000);

    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.quit();
    }
}

winner2_game_2winner_quest();




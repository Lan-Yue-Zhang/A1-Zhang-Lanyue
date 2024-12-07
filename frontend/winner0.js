const { Builder, By, until } = require('selenium-webdriver');

async function winner0_quest() {
    let driver = await new Builder().forBrowser('chrome').build();
    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.xpath("//button[contains(text(), '0_winner_quest')]"));
        await startButton.click();

        await driver.wait(until.elementTextContains(driver.findElement(By.id('game-status')), 'Game started'), 1000);
        console.log("Game started successfully.");
        // Start game
        let handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.log("winner0 initial hand : " + handStatus);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(50) F(70) D(5) D(5) S(10) S(10) H(10) H(10) B(15) B(15) L(20) L(20)"),"error at initial P1 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(5) F(10) F(15) F(15) F(20) F(20) F(25) F(30) F(30) F(40) E(30)"),"error at initial P2 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(10) F(15) F(15) F(20) F(20) F(25) F(25) F(30) F(40) L(20)"),"error at initial P3 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(5) F(5) F(10) F(15) F(15) F(20) F(20) F(25) F(25) F(30) F(50) E(30)"),"error at initial P4 hand");

        let shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.log("winner0 initial shield : " + shieldStatus);
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:0    P3 shields:0    P4 shields:0"),"error at initial shield");
        let numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.log("winner0 initial cards number : " + numStatus);
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at initial cards number");

        //P1 draws a quest of 2 stages
        let drawButton = await driver.findElement(By.xpath("//button[contains(text(), 'Draw Event card')]"));
        await drawButton.click();
        await driver.sleep(1000);
        let gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The current player has drawn an 2 stage") ,"error at drawn an 2 stage");

        //P1 is asked and sponsors and then builds the 4 stages
        let yesButton = await driver.findElement(By.xpath("//button[contains(text(), 'Yes')]"));
        await yesButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("P1 is sponsor") ,"error at P1 is asked and sponsors");
        let playerCards = await driver.findElements(By.css("#player-cards .card"));
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");

        let cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(50)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'L(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        let quitButton = await driver.findElement(By.xpath("//button[contains(text(), 'Quit')]"));
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 6, "error at player should have 6 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 1 is 110 : F(50) D(5) S(10) H(10) B(15) L(20)") ,"error at drawn cards in stage 1");

        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(70)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'D(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'S(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'H(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'B(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'L(20)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 0, "error at player should have 0 cards");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P1 in stage 2 is 130 : F(70) D(5) S(10) H(10) B(15) L(20)") ,"error at drawn cards in stage 2");

        let leaveButton = await driver.findElement(By.xpath("//button[contains(text(), 'Leave')]"));
        await leaveButton.click();
        await driver.sleep(1000);

        //p2
        await yesButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(5) F(5) F(10) F(15) F(15) F(20) F(20) F(25) F(30) F(30) F(40) E(30)"),"error at P2 draws F5");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:13    P3 cards:12    P4 cards:12") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(5) F(10) F(15) F(15) F(20) F(20) F(25) F(30) F(30) F(40) E(30)"),"error at P2 discards an F5");
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
        console.assert(handStatus.includes("P3: F(5) F(5) F(10) F(15) F(15) F(15) F(20) F(20) F(25) F(25) F(30) F(40) L(20)"),"error at P3 participates, draws 1xF15");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:13    P4 cards:12") ,"error at cards number");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(15)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(10) F(15) F(15) F(20) F(20) F(25) F(25) F(30) F(40) L(20)"),"error at P3 discards F15");
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
        console.assert(handStatus.includes("P4: F(5) F(5) F(10) F(10) F(15) F(15) F(20) F(20) F(25) F(25) F(30) F(50) E(30)"),"error at P2 participates, draws 1xF10");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 13, "error at player should have 13 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:13") ,"error at cards number P4 discards 1xF10");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(5) F(5) F(10) F(15) F(15) F(20) F(20) F(25) F(25) F(30) F(50) E(30)"),"error at P4 discards 1xF10");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:12    P3 cards:12    P4 cards:12") ,"error at cards number P4 discards 1xF10");
        await leaveButton.click();
        await driver.sleep(1000);

        // Stage 1:
        //p2
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'E(30)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("The value of the card used by P2 in stage 1 is 30 : E(30) current stage fail") ,"error at P2 attack: Excalibur thus loses");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 11, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number P2 attack: Excalibur");
        await leaveButton.click();
        await driver.sleep(1000);

        //p3
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("empty set of non repeated weapon cards") ,"error at P3 plays nothing as attack and thus loses");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 11 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number P3 plays nothing");
        await leaveButton.click();
        await driver.sleep(1000);

        //p4
        await quitButton.click();
        await driver.sleep(1000);
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("empty set of non repeated weapon cards") ,"error at P4 plays nothing as attack and thus loses");
        console.assert(gameStatus.includes("The quest ends with no winner") ,"error at The quest ends with no winner");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:0    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at The quest ends with no winner but P1 does discards 12 quests cards");
        await leaveButton.click();
        await driver.sleep(1000);

        //p1
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(5) F(10) F(15) D(5) D(5) D(5) D(5) S(10) S(10) S(10) H(10) H(10) H(10) H(10)"),"error at P1 draws 14 cards: 1xF5, 1xF10, 1xF15, 4 daggers, 4 horses, 3 swords");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 14, "error at player should have 14 cards");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:14    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at cards number P1 draws 14 cards");
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(5)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        cardButton = await driver.findElement(By.xpath("//button[contains(text(), 'F(10)')]"));
        await cardButton.click();
        await driver.sleep(1000);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(15) D(5) D(5) D(5) D(5) S(10) S(10) S(10) H(10) H(10) H(10) H(10)"),"error at P1 discards 1xF5, 1x10 ");
        playerCards = await driver.findElements(By.css("#player-cards .card"));
        console.assert(playerCards.length == 12, "error at player should have 12 cards");

        console.log("winner0 final hand : " + handStatus);
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P1: F(15) D(5) D(5) D(5) D(5) S(10) S(10) S(10) H(10) H(10) H(10) H(10)"),"error at final P1 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P2: F(5) F(5) F(10) F(15) F(15) F(20) F(20) F(25) F(30) F(30) F(40)"),"error at final P2 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P3: F(5) F(5) F(10) F(15) F(15) F(20) F(20) F(25) F(25) F(30) F(40) L(20)"),"error at final P3 hand");
        handStatus = await driver.findElement(By.id('display_hands')).getText();
        console.assert(handStatus.includes("P4: F(5) F(5) F(10) F(15) F(15) F(20) F(20) F(25) F(25) F(30) F(50) E(30)"),"error at final P4 hand");

        shieldStatus = await driver.findElement(By.id('player-shields')).getText();
        console.log("winner0 final shield : " + shieldStatus);
        console.assert(shieldStatus.includes("P1 shields:0    P2 shields:0    P3 shields:0    P4 shields:0"),"error at final shield");
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.log("winner0 final cards number : " + numStatus);
        numStatus = await driver.findElement(By.id('player-cards-number')).getText();
        console.assert(numStatus.includes("P1 cards:12    P2 cards:11    P3 cards:12    P4 cards:12") ,"error at final cards number");
        gameStatus = await driver.findElement(By.id('game-status')).getText();
        console.assert(gameStatus.includes("No winner of game, continues the game") ,"error at No winner of game, continues the game");

        await driver.sleep(3000);
    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.quit();
    }
}

async function runScenarios() {
    await winner0_quest();
}

runScenarios();



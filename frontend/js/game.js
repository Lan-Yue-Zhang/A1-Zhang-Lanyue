const apiBaseUrl = "http://localhost:8080";

let input = "";
let GameState = "";

async function startGame(){
    try {
        const response = await fetch(`${apiBaseUrl}/start`);
        const result = await response.text();
        console.log("Start Game Response:", result);
        document.getElementById("game-container").style.visibility = "visible";
        document.getElementById("draw-button").disabled = false;
        document.getElementById("Quit-button").disabled = true;
        document.getElementById("Yes-button").disabled = true;
        document.getElementById("No-button").disabled = true;
        document.getElementById("Leave-button").disabled = true;
        updatePlayer();
        updateShield();
        updateCard();
        updateStage();
        document.getElementById("game-status").innerText = result;
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}
async function winner2(){
    try {
        const response = await fetch(`${apiBaseUrl}/2_winner`);
        const result = await response.text();
        console.log("Start Game Response:", result);
        document.getElementById("game-container").style.visibility = "visible";
        document.getElementById("draw-button").disabled = false;
        document.getElementById("Quit-button").disabled = true;
        document.getElementById("Yes-button").disabled = true;
        document.getElementById("No-button").disabled = true;
        document.getElementById("Leave-button").disabled = true;
        updatePlayer();
        updateShield();
        updateCard();
        updateStage();
        document.getElementsByTagName('h1')[0].innerText = "winner2";
        document.getElementById("game-status").innerText = result;
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}
async function winner1(){
    try {
        const response = await fetch(`${apiBaseUrl}/1_winner`);
        const result = await response.text();
        console.log("Start Game Response:", result);
        document.getElementById("game-container").style.visibility = "visible";
        document.getElementById("draw-button").disabled = false;
        document.getElementById("Quit-button").disabled = true;
        document.getElementById("Yes-button").disabled = true;
        document.getElementById("No-button").disabled = true;
        document.getElementById("Leave-button").disabled = true;
        updatePlayer();
        updateShield();
        updateCard();
        updateStage();
        document.getElementsByTagName('h1')[0].innerText = "winner1";
        document.getElementById("game-status").innerText = result;
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}
async function winner0(){
    try {
        const response = await fetch(`${apiBaseUrl}/0_winner`);
        const result = await response.text();
        console.log("Start Game Response:", result);
        document.getElementById("game-container").style.visibility = "visible";
        document.getElementById("draw-button").disabled = false;
        document.getElementById("Quit-button").disabled = true;
        document.getElementById("Yes-button").disabled = true;
        document.getElementById("No-button").disabled = true;
        document.getElementById("Leave-button").disabled = true;
        updatePlayer();
        updateShield();
        updateCard();
        updateStage();
        document.getElementsByTagName('h1')[0].innerText = "winner0";
        document.getElementById("game-status").innerText = result;
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}
async function A1_scenario(){
    try {
        const response = await fetch(`${apiBaseUrl}/A1_scenario`);
        const result = await response.text();
        console.log("Start Game Response:", result);
        document.getElementById("game-container").style.visibility = "visible";
        document.getElementById("draw-button").disabled = false;
        document.getElementById("Quit-button").disabled = true;
        document.getElementById("Yes-button").disabled = true;
        document.getElementById("No-button").disabled = true;
        document.getElementById("Leave-button").disabled = true;
        updatePlayer();
        updateShield();
        updateCard();
        updateStage();
        document.getElementsByTagName('h1')[0].innerText = "A1_scenario";
        document.getElementById("game-status").innerText = result;
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}
async function updatePlayer(){
    try {
        const response = await fetch(`${apiBaseUrl}/updatePlayer`, {
            method: "GET"
        });
        const responseText = await response.text();
        console.log("updatePlayer Response:", responseText);
        document.getElementById("player-cards").innerHTML = '';
        let words = separateBySpace(responseText);
        document.getElementById("player-id").innerText = words.pop();
        words.forEach(word => addCard(word));
    } catch (error) {
        console.error("Error in updatePlayer:", error);
    }
}
async function updateShield(){
    try {
        const response = await fetch(`${apiBaseUrl}/updateShield`, {
            method: "GET"
        });
        const responseText = await response.text();
        console.log("updateShield Response:", responseText);
        document.getElementById("player-shields").innerText = responseText;
    } catch (error) {
        console.error("Error in updateShield:", error);
    }
}
async function updateCard(){
    try {
        updateCardNumber();
        const response = await fetch(`${apiBaseUrl}/updateCard`, {
            method: "GET"
        });
        const responseText = await response.text();
        console.log("updateCard Response:", responseText);
        document.getElementById("display_hands").innerText = responseText;
    } catch (error) {
        console.error("Error in updateCard:", error);
    }
}
async function updateStage(){
    try {
        const response = await fetch(`${apiBaseUrl}/updateStage`, {
            method: "GET"
        });
        const responseText = await response.text();
        console.log("updateStage Response:", responseText);
        document.getElementById("Stage").innerText = responseText;
    } catch (error) {
        console.error("Error in updateStage:", error);
    }
}
async function updateCardNumber(){
    try {
        const response = await fetch(`${apiBaseUrl}/updateCardNumber`, {
            method: "GET"
        });
        const responseText = await response.text();
        console.log("updateCard Response:", responseText);
        document.getElementById("player-cards-number").innerText = responseText;
    } catch (error) {
        console.error("Error in updateCardNumber:", error);
    }
}

async function drawEventCard(){
    try {
        const response = await fetch(`${apiBaseUrl}/drawEventCard`, {
            method: "GET"
        });
        const responseText = await response.text();
        console.log("drawEventCard Response:", responseText);
        document.getElementById("game-status").innerText = responseText;
        document.getElementById("draw-button").disabled = true;
        if (responseText.includes("Quest card")) {
            askSponsor();
        } else {
            updatePlayer();
            updateCard();
            updateShield();
            remove_check();
        }
    } catch (error) {
        console.error("Error in drawEventCard:", error);
    }
}
async function remove_check() {
    try {
        const response = await fetch(`${apiBaseUrl}/remove_check`, {
            method: "GET",
        });
        const responseText = await response.text();
        console.log("remove_check Response:", responseText);
        const gameStatusElement = document.getElementById("game-status");
        const leaveButton = document.getElementById("Leave-button");
        const playerId = document.getElementById("player-id")?.innerText;

        if (responseText.trim() === "false") {
            CannotSelectCard();
            GameState = "";
            gameStatusElement.innerText += "  Please leave the hot seat.";
            leaveButton.disabled = false;
        } else if (responseText.trim() === playerId) {
            if (!gameStatusElement.innerText.includes("You need to remove cards")) {
                gameStatusElement.innerText += " You need to remove cards";
            }
            startRemove();
            document.getElementById("Yes-button").disabled = true;
            document.getElementById("No-button").disabled = true;

        } else if (responseText.trim() === "getParticipants") {
            CannotSelectCard();
            document.getElementById("Yes-button").disabled = true;
            document.getElementById("No-button").disabled = true;
            gameStatusElement.innerText += "  Please leave the hot seat.";
            leaveButton.disabled = false;
        } else if (responseText.trim() === "next_round") {
            CannotSelectCard();
            winner_check();
        } else {
            CannotSelectCard();
            gameStatusElement.innerText += "  Please leave the hot seat.";
            leaveButton.disabled = false;
        }
    } catch (error) {
        console.error("Error in remove_check:", error);
    }
}
async function winner_check() {
    try {
        const response = await fetch(`${apiBaseUrl}/winner`, {
            method: "GET",
        });
        const responseText = await response.text();
        console.log("winner_check Response:", responseText);
        document.getElementById("game-status").innerText = responseText;
        if (responseText.includes("The winner is")) {
            document.getElementById("draw-button").disabled = true;
            document.getElementById("Quit-button").disabled = true;
            document.getElementById("Yes-button").disabled = true;
            document.getElementById("No-button").disabled = true;
            document.getElementById("Leave-button").disabled = true;
        } else {
            document.getElementById("game-status").innerText += "  Please leave the hot seat.";
            document.getElementById("Leave-button").disabled = false;
        }
    } catch (error) {
        console.error("Error in winner_check:", error);
    }
}

async function leave() {
    try {
        const response = await fetch(`${apiBaseUrl}/leave`, {
            method: "GET"
        });
        const responseText = await response.text();
        console.log("leave Response:", responseText);
        const gameStatusElement = document.getElementById("game-status");
        gameStatusElement.innerText = "";
        updatePlayer();
        updateStage();
        document.getElementById("Leave-button").disabled = true;
        if (responseText.includes("check_next_round")) {
            remove_check();
        } else if (responseText.includes("getParticipants")) {
            askParticipants();
        } else if (responseText.includes("Get_sponsor")) {
            askSponsor();
        } else if (responseText.includes("next_round")) {
            updateCard();
            remove_check();
        } else if (responseText.includes("new_round")) {
            gameStatusElement.innerText = "new round draw event card";
            document.getElementById("draw-button").disabled = false;
            document.getElementById("Quit-button").disabled = true;
            document.getElementById("Yes-button").disabled = true;
            document.getElementById("No-button").disabled = true;
        } else if (responseText.includes("start_stage")) {
            startStage();
        }
    } catch (error) {
        console.error("Error in leave:", error);
    }
}
function askSponsor(){
    document.getElementById("game-status").innerText += " Do you want to sponsor the current task?";
    document.getElementById("Yes-button").setAttribute("onclick", "getSponsor('Y')");
    document.getElementById("No-button").setAttribute("onclick", "getSponsor('N')");
    document.getElementById("Yes-button").disabled = false;
    document.getElementById("No-button").disabled = false;
}
function askParticipants(){
    document.getElementById("game-status").innerText = " Do you want to join the current task?";
    document.getElementById("Yes-button").setAttribute("onclick", "getParticipants('Y')");
    document.getElementById("No-button").setAttribute("onclick", "getParticipants('N')");
    document.getElementById("Yes-button").disabled = false;
    document.getElementById("No-button").disabled = false;
}
async function getSponsor(ans){
    try {
        ans += "\n";
        ans += " \n";
        const response = await fetch(`${apiBaseUrl}/Get_sponsor?input=${encodeURIComponent(ans)}`, {
            method: "POST"
        });
        const result = await response.text();
        console.log("getSponsor Response:", result);
        if (result.includes("set_stage")) {
            setStage();
            document.getElementById("game-status").innerText = document.getElementById("player-id").innerText
            + " is sponsor Enter the card you want to build this stage (e.g. S(10)): ";
            document.getElementById("Quit-button").disabled = false;
            document.getElementById("Yes-button").disabled = true;
            document.getElementById("No-button").disabled = true;
        } else if (result.includes("next_round")) {
            document.getElementById("Yes-button").disabled = true;
            document.getElementById("No-button").disabled = true;
            document.getElementById("game-status").innerText = "no Sponsor  Please leave the hot seat.";
            document.getElementById("Leave-button").disabled = false;
        } else {
            document.getElementById("game-status").innerText = document.getElementById("player-id").innerText
                        + " is not sponsor";
            document.getElementById("Yes-button").disabled = true;
            document.getElementById("No-button").disabled = true;
            document.getElementById("game-status").innerText += "  Please leave the hot seat.";
            document.getElementById("Leave-button").disabled = false;
        }
    } catch (error) {
        console.error("Error in getSponsor", error);
    }

}
async function getParticipants(ans){
    try {
        const response = await fetch(`${apiBaseUrl}/Get_Participants?input=${encodeURIComponent(ans)}`, {
            method: "POST"
        });
        const result = await response.text();
        console.log("getParticipants Response:", result);

        if (ans.includes("Y")){
            document.getElementById("game-status").innerText = document.getElementById("player-id").innerText
            + " participate in " + document.getElementById("Stage").innerText;
        } else {
            document.getElementById("game-status").innerText = document.getElementById("player-id").innerText
            + "declines to participate in " + document.getElementById("Stage").innerText;
        }
        updateCard();
        if (result.includes("remove card")) {
            remove_check();
        } else {
            var num = document.getElementById("player-id").innerText;
            num = num.replace("P", "");
            updateSelectPlayer(num);
            document.getElementById("Yes-button").disabled = true;
            document.getElementById("No-button").disabled = true;
            document.getElementById("game-status").innerText += "  Please leave the hot seat.";
            document.getElementById("Leave-button").disabled = false;
        }

    } catch (error) {
        console.error("Error in getParticipants", error);
    }
}
async function updateSelectPlayer(num){
    try {
        const response = await fetch(`${apiBaseUrl}/updateSelectPlayer?input=${encodeURIComponent(num)}`, {
            method: "GET"
        });
        const responseText = await response.text();
        console.log("updatePlayer Response:", responseText);
        document.getElementById("player-cards").innerHTML = '';
        let words = separateBySpace(responseText);
        document.getElementById("player-id").innerText = words.pop();
        words.forEach(word => addCard(word));
    } catch (error) {
        console.error("Error in updatePlayer:", error);
    }
}
function separateBySpace(input) {
    const words = input.trim().split(/\s+/);
    return words;
}

function CanSelectCard() {
    var btns = document.getElementsByClassName("card");
    for (var i = 0; i < btns.length; i++) {
      btns[i].disabled = false;
    }
}
function CannotSelectCard() {
    var btns = document.getElementsByClassName("card");
    for (var i = 0; i < btns.length; i++) {
      btns[i].disabled = true;
    }
}
async function startRemove(){
    try {
        const response = await fetch(`${apiBaseUrl}/updatePlayer`, {
            method: "GET"
        });
        const responseText = await response.text();
        console.log("startRemove Response:", responseText);
        document.getElementById("player-cards").innerHTML = '';
        let words = separateBySpace(responseText);
        document.getElementById("player-id").innerText = words.pop();
        words.forEach(word => addRemoveCard(word));
    } catch (error) {
        console.error("Error in startRemove:", error);
    }
}
function addRemoveCard(c) {
    var card = document.createElement("button");
    card.innerText = c;
    card.setAttribute("class", "card");
    card.setAttribute("onclick", "sendRemoveCard(this,'" + c + "')");
    document.getElementById("player-cards").appendChild(card);
}
function setStage() {
    addSponsorCard();
    CanSelectCard();
    updateStage();
    document.getElementById("Quit-button").setAttribute("onclick", "sponsorQuit()");
}
async function startStage(){
    try {
        const response = await fetch(`${apiBaseUrl}/updatePlayer`, {
            method: "GET"
        });
        const responseText = await response.text();
        console.log("startStage Response:", responseText);
        document.getElementById("player-cards").innerHTML = '';
        let words = separateBySpace(responseText);
        document.getElementById("player-id").innerText = words.pop();
        words.forEach(word => addCard(word));
        CanSelectCard();
        document.getElementById("Quit-button").setAttribute("onclick", "ParticipantsQuit()");
        document.getElementById("Quit-button").disabled = false;
    } catch (error) {
        console.error("Error in startStage:", error);
    }
}
async function sponsorQuit(){
    try {
        const response = await fetch(`${apiBaseUrl}/set_stage_Quit`, {
            method: "GET"
        });
        const result = await response.text();
        console.log("sponsorQuit Response:", result);
        if (result.includes("getParticipants")) {
            RemoveCard();
            document.getElementById("Quit-button").disabled = true;
            CannotSelectCard();
            document.getElementById("game-status").innerText += "  Please leave the hot seat.";
            document.getElementById("Leave-button").disabled = false;
        } else if (result.includes("true")) {
            RemoveCard();
            updateStage();
        } else {
            Remove_activeCard();
            document.getElementById("game-status").innerText = result;
        }
    } catch (error) {
        console.error("Error in sponsorQuit", error);
    }
}
async function ParticipantsQuit(){
    try {

        const response = await fetch(`${apiBaseUrl}/startStage_Quit`, {
            method: "GET"
        });
        const result = await response.text();
        console.log("ParticipantsQuit Response:", result);
        RemoveCard();
        CannotSelectCard();
        document.getElementById("Quit-button").disabled = true;
        updateCard();
        updateShield();
        document.getElementById("game-status").innerText += " " + result;
        document.getElementById("Yes-button").disabled = true;
        document.getElementById("No-button").disabled = true;
        document.getElementById("game-status").innerText += "  Please leave the hot seat.";
        document.getElementById("Leave-button").disabled = false;
    } catch (error) {
        console.error("Error in ParticipantsQuit", error);
    }
}
async function display_set_stage(){
    try {
        const response = await fetch(`${apiBaseUrl}/display_set_stage`, {
            method: "GET"
        });
        const result = await response.text();
        console.log("display_set_stage Response:", result);
        document.getElementById("game-status").innerText = result;
    } catch (error) {
        console.error("Error in display_set_stage", error);
    }
}
async function display_stage(){
    try {

        const response = await fetch(`${apiBaseUrl}/display_stage`, {
            method: "GET"
        });
        const result = await response.text();
        console.log("display_stage Response:", result);
        document.getElementById("game-status").innerText = result;
    } catch (error) {
        console.error("Error in display_stage", error);
    }
}
function addCard(c) {
    var card = document.createElement("button");
    card.innerText = c;
    card.setAttribute("class", "card");
    card.setAttribute("onclick", "sendCard(this,'" + c + "')");
    card.disabled = true;
    document.getElementById("player-cards").appendChild(card);
}

function addSponsorCard() {
    var btns = document.getElementsByClassName("card");
    for (var i = 0; i < btns.length; i++) {
      btns[i].setAttribute("onclick", "sendSponsorCard(this,'" + btns[i].innerText + "')");
    }
}
function RemoveCard() {
    var btns = document.getElementsByClassName("active");
    for (var i = btns.length - 1; i >= 0; i--) {
        btns[i].remove();
    }
}

function Remove_activeCard() {
    var btns = document.getElementsByClassName("active");
    for (var i = btns.length - 1; i >= 0; i--) {
        btns[i].classList.remove("active");
    }
}

async function sendRemoveCard(element, card) {
    try {
        card += "\n";
        const response = await fetch(`${apiBaseUrl}/remove_cards?input=${encodeURIComponent(card)}`, {
            method: "POST"
        });
        let result = await response.text();
        console.log("sendRemoveCard Response:", result);
        if (result.includes("removed")) {
            if (!element.classList.contains("active")) {
                element.classList.add("active");
            }
            document.getElementById("game-status").innerText = result;
            updateCard();
            RemoveCard();
            remove_check();
        } else {
            document.getElementById("game-status").innerText = result;
        }
    } catch (error) {
        console.error("Error sending card:", error);
    }
}

async function sendCard(element, card) {
    try {
        const response = await fetch(`${apiBaseUrl}/startStage?input=${encodeURIComponent(card)}`, {
            method: "POST"
        });
        let result = await response.text();
        console.log("sendCard Response:", result);
        if (result.includes("true")) {
            if (!element.classList.contains("active")) {
                element.classList.add("active");
            }
            console.log("Card sent:", card);
            display_stage();
        } else {
            document.getElementById("game-status").innerText = result;
        }
    } catch (error) {
        console.error("Error sending card:", error);
    }
}

async function sendSponsorCard(element, card) {
    try {
        const response = await fetch(`${apiBaseUrl}/set_stage?input=${encodeURIComponent(card)}`, {
            method: "POST"
        });
        let result = await response.text();
        console.log("sendSponsorCard Response:", result);
        if (result.includes("true")) {
            if (!element.classList.contains("active")) {
                element.classList.add("active");
            }
            input += card +"\n";
            console.log("Card sent:", card);
            display_set_stage();
        } else {
            document.getElementById("game-status").innerText = result;
        }
    } catch (error) {
        console.error("Error sendSponsorCard:", error);
    }
}







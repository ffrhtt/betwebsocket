const baseUrl = "localhost:8080";

const stompClient = new StompJs.Client({
    brokerURL: "ws://" + baseUrl + "/chat"
});
function getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}
stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages', (response) => {
        let responseJson = JSON.parse(response.body);
        console.log('Received message:'+ responseJson);
        showGreeting(responseJson.from + ',' + responseJson.text + ',' + responseJson.time);
    });
    stompClient.subscribe('/topic/bet-live-rates', (response) => {
        let responseJson = JSON.parse(response.body);
        console.log('Received message:'+ responseJson);
        jQuery.each(responseJson, function(key, value) {
            $('#ms1_id'+key).html(value.rateMs1).css('background-color', getRandomColor());;
            $('#msX_id'+key).html(value.rateMsX).css('background-color', getRandomColor());;
            $('#ms2_id'+key).html(value.rateMs2).css('background-color', getRandomColor());;
        });
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/message",
        body: JSON.stringify({'from': $("#name").val(),'text': "Hello World Websocket Firt Message"})
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});
// A $( document ).ready() block.
$( document ).ready(function() {
    console.log( "ready!" );
    getAllMatches();
    connect();
});
function getAllMatches() {
    var settings = {
      "url": "http://" + baseUrl + "/api/v1/match",
      "method": "GET",
      "timeout": 0,
    };
    $.ajax(settings).done(function (response) {
        response.forEach((item) => {
            addMatchRow(item.league, item.id, item.homeTeam, item.guestTeam, item.matchDate);
        });
    });
}

function addMatch() {
    const league = $('#league').val();
    const homeTeam = $('#homeTeam').val();
    const guestTeam = $('#guestTeam').val();
    const matchDate = $('#matchDate').val();
    var settings = {
      "url": "http://" + baseUrl + "/api/v1/match",
      "method": "POST",
      "timeout": 0,
      "headers": {
        "Content-Type": "application/json"
      },
      "data": JSON.stringify({
        "league": league,
        "homeTeam": homeTeam,
        "guestTeam": guestTeam,
        "matchDate": matchDate
      }),
    };

    $.ajax(settings).done(function (response) {
        console.log(response);
        addMatchRow(league, response.id, homeTeam, guestTeam, matchDate);
        clearForm();
    })
    .fail(function(msg) {
       alert("Error: "+ msg.responseText );
    });
    // Clear input fields
}
function clearForm() {
    document.getElementById('homeTeam').value = '';
    document.getElementById('guestTeam').value = '';
}

function addMatchRow(league, id, homeTeam, guestTeam, matchDate){
    const matchContainer = document.createElement('div');
    matchContainer.className = 'match';
    document.getElementById('matches').appendChild(matchContainer);
    matchContainer.innerHTML = `
        <h3 id="match-title">${homeTeam} vs ${guestTeam} <i id="match-date" style="font-size: 12px;">${matchDate}</i></h3>
        <div class="odds">
            <button id="btn_league">${league}</button>
            <button onclick="doCoupon(${id}, 'ms1')">MS1: <label id="ms1_id${id}">...</label></button>
            <button onclick="doCoupon(${id}, 'msX')">MSX: <label id="msX_id${id}">...</label></button>
            <button onclick="doCoupon(${id}, 'ms2')">MS2: <label id="ms2_id${id}">...</label></button>
        </div>
    `;
}

function doCoupon(id, type) {
    let text;
    let couponCount = prompt("Kaç Adet Kupon Almak İstiyorsunuz?", "1");
    console.log(`Coupon ${type} for match ${id} clicked, ${couponCount} times`);

    var settings = {
      "url": "http://" + baseUrl + "/api/v1/coupon",
      "method": "POST",
      "timeout": 0,
      "headers": {
        "Content-Type": "application/json"
      },
      "data": JSON.stringify({
        "matchId": "1",
        "ms1": $('#ms1_id'+id).html(),
        "msX": $('#msX_id'+id).html(),
        "ms2": $('#ms2_id'+id).html(),
        "msS": type,
        "count": couponCount
      }),
    };

    $.ajax(settings).done(function (response) {
      console.log(response);
      alert(response);
    })
    .fail(function(msg) {
       alert("Error: "+ msg.responseText );
    });
}
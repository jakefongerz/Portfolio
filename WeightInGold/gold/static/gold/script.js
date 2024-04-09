let protocol = 'http';
let hostname = 'localhost:8000';

const apiKey = 'ztxmKzfqvTTsP-JVP5YF'

// Global variables allow us to examine the results of asynchronous requests
var theData;
var theUnitData;
var thePrice;
var weightInToz;
var valNew;


// Callback for "Fetch!" button's onclick event
var go_fetch = function() {
    let url = "https://data.nasdaq.com/api/v3/datasets/LBMA/GOLD?limit=1&api_key=" + apiKey
    if (url) {
        document.querySelector('#gold_price').innerHTML = "i am getting the data right now!!!! :)";
        // make a GET request against the chosen URL
        fetch(url)
            .then(response => {
                return response.json();
                })
            .then(json => {
                theData = json;
                thePrice = theData.dataset.data[0][1];
                let current_date = theData.dataset.newest_available_date
                document.querySelector('#gold_price').innerHTML = "As of <em>" + current_date + "</em> the current price of gold is <b>$" + thePrice + "</b> per troy oz"
                console.log(theData);

            })
            .catch(error => {
                console.error('<==========Error==========>:\n', error);
                document.querySelector('#gold_price').innerHTML = "Could not connect to Nasdaq! " + error
            });

    }
}

function fetch_unitconv(frm, val) {
    let url = 'http://localhost:8000/unitconv/convert/?from=' + frm + '&to=t_oz' + '&value=' + val;
    if (url) {
        return fetch(url)
            .then(response => response.json())
            .then(json => {
                theUnitData = json;
                const valNew = theUnitData.value;
                console.log(val + " " + frm + " is equal to " + valNew + " troy oz")
                return valNew;
            })
            .catch(error => {
                console.log("something went wrong " + error)
            });
    }
}

async function createDiv() {
  var div = document.createElement("div");
  var frm = document.getElementById("units").value;
  var val = document.getElementById("weight").value;
  if (isInputGood(val, frm)) {
  weightInToz = await fetch_unitconv(frm, val);
  let totalVal = thePrice * weightInToz
  div.innerHTML = val + " " + frm + " is worth $" + totalVal.toFixed(2) + " in gold!";
  }
  else {
  div.innerHTML = "bro make sure that \"" + val +"\" is a positive number and \"" + frm + "\" is from the list given :("
  }
  // attributes for the div
  div.className = "stuff-box";
  div.style.backgroundColor = "rgb(255, 192, 0)";


  div.addEventListener("click", function() {
    this.remove();
  });

  // add div to top
  var container = document.getElementById("container");
  container.insertBefore(div, container.firstChild);
}

function isInputGood(weight, option){
    const options = ["lb", "T", "g", "t_oz", "kg", "oz"];
    if ((isNaN(weight)) || (weight < 0)) {
        return false
    }
    else if (options.includes(option) == false) {
        return false
    }
    else {
    return true}

}




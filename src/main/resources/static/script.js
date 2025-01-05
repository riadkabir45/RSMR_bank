// Example JavaScript (If needed for interactivity in future enhancements)
document.addEventListener("DOMContentLoaded", function() {
    console.log("RSMR Bank Dashboard loaded successfully!");

    const colorElems = document.querySelectorAll(".color-primary");
/*
    setInterval(() => {
        const randomColor = getRandomColor();
      
        colorElems.forEach(element => {
          element.style.backgroundColor = randomColor;
        });
        console.log(randomColor);
      }, 50);

    console.log("WTF");
    c = 255*sin(x)
    (c-127)/127
    */
    const initColor = [Math.random()*255,Math.random()*255, Math.random()*255];
    console.log(initColor);
    let offset = 0;

    setInterval(() => {
        offset += 1;
        if (offset >= 360)
            offset = 0;

        const color = initColor.map(number => 127 + 127*Math.sin((number+offset)*2*Math.PI/360));
        colorElems.forEach(element => {
          element.style.backgroundColor = `rgb(${color[0]},${color[1]},${color[2]})`;
        });
      }, 50);

});

function sendRequest(url) {
    fetch(url)
        .then(response => {
            // Check the HTTP status code
            if (response.status === 302) { // Adjust the status code as needed
                window.location.reload();
            }
            else if (!response.ok) {
                throw new Error('Network response was not ok: '+response.status);
            } else {
                // Handle other status codes or display a message
                console.log('Request successful, but status code:', response.status);
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            // Handle errors, e.g., display an error message to the user
        });
}


function getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function getNormalTime(isoString){

const date = new Date(isoString);
  const options = { 
    year: 'numeric', 
    month: 'long', 
    day: 'numeric', 
    hour: '2-digit', 
    minute: '2-digit', 
    second: '2-digit' 
  };

  const formattedDate = date.toLocaleString('en-US', options); 

  return formattedDate;
}

function sendAndAct(url,event,actionHandler){
  event.preventDefault(); 

    const formData = new FormData(event.target);

    fetch(url, {
      method: 'POST',
      body: formData
    })
    .then(response => {
      if (!(response.ok || response.found)) {
        throw new Error('Network response was not ok');
      }
      //return response.json(); 
    })
    .then(data => {
      actionHandler(data); 
    })
    .catch(error => {
      console.error('There has been a problem with your fetch operation:', error);
    });
}

function callAndAct(url,event,actionHandler){
  event.preventDefault(); 

    const formData = new FormData(event.target);

    fetch(url, {
      method: 'GET'
    })
    .then(response => {
      if (!(response.ok || response.found)) {
        throw new Error('Network response was not ok');
      }
      //return response.json(); 
    })
    .then(data => {
      actionHandler(data); 
    })
    .catch(error => {
      console.error('There has been a problem with your fetch operation:', error);
    });
}

function reload(){
  window.location.reload(); 
}
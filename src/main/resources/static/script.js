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
// Example JavaScript (If needed for interactivity in future enhancements)
document.addEventListener("DOMContentLoaded", function() {
    console.log("RSMR Bank Dashboard loaded successfully!");
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
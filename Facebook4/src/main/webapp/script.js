document.addEventListener("DOMContentLoaded", () => {
    var modal = document.getElementById("myModal");
    let profileImage = document.getElementById("profileImage");
    var signupbtn = document.getElementById("signform");
    var span = document.getElementsByClassName("close")[0];
    let Buttons = document.getElementById("myDropdown");

    // Open modal
    if (signupbtn) {
        signupbtn.addEventListener("click", (event) => {
            event.preventDefault(); // Prevent default form submission
            if (modal) {
                modal.style.display = "block";
               
            }
        });
    }

    // Show dropdown when profile image is clicked
    if (profileImage && Buttons) {
        profileImage.addEventListener("click", () => {
            Buttons.style.display = "block"; // Show buttons
        });
    }

    // Hide dropdown when clicking outside of it
    document.addEventListener("click", (event) => {
        if (profileImage && Buttons && !profileImage.contains(event.target) && !Buttons.contains(event.target)) {
            Buttons.style.display = "none"; // Hide buttons if clicked outside
        }
    });

    // Close the modal when the close button is clicked
    if (span) {
        span.addEventListener("click", () => {
            if (modal) {
                modal.style.display = "none"; // Hide modal
            }
        });
    }

    // Close the modal when clicking outside of it
    window.addEventListener("click", (event) => {
        if (modal && event.target === modal) {
            modal.style.display = "none"; // Hide modal
        }
    });
});

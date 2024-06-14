function loginUser(event) {
    event.preventDefault();
    const username = document.getElementById("login_username").value;
    const password = document.getElementById("login_password").value;
    console.log(username);
  
    if (username.trim() === "" || password.trim() === "") {
      alert("Please enter username and password");
      return;
    }

    console.log(username);
  
    fetch("http://localhost:8080/api/user/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username, password }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Could not login");
        }
        return response.json();
      })
      .then((data) => {
        if (data) {
          console.log("Successfully logged In");
          window.location.href = `index.html?user=${JSON.stringify(data.user)}`;
        } else {
          alert("Invalid username or password");
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("Could not login");
      });
  }
  
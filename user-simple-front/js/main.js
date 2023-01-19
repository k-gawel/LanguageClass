function login() {
  var username = document.getElementById("login-username").value;
  var password = document.getElementById("login-password").value;

  const params = {
    username: username,
    password: password
  }

  const options = {
    method: 'POST',
    body: JSON.stringify(params),

    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*"
      },
  }

  fetch('http://localhost:8082/login', options)
    .then(response => response.json())
    .then(response => console.log(JSON.stringify(response)))
    .catch(err => console.log(err))
}

function registerStudent() {

}

function registerTeacher() {

}

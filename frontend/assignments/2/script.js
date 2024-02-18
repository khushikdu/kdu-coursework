document.addEventListener("DOMContentLoaded", function () {
  const socket = io("http://localhost:8080");

  const form=document.getElementsByClassName("send-message");
  const input=document.getElementById("messageInput");
  const messageContainer=document.querySelector("chat-area");

  /**whenever a new user joins */
  let newUsername="User1";
  socket.emit('new_connection_join',newUsername);

  function showSidebar() {
    sidebar = document.querySelector(".mob_nav");
    sidebar.style.display = "inline";
  }

  function blockDisplay() {
    document.write("in btn func");
    sidebar = document.querySelector(".mob_nav");
    sidebar.style.display = "none";
  }



  function addPost() {
    // Get the input value
    let postText = document.getElementById("postInput").value;

    if (postText.trim() !== "") {
      // Create a new post element
      let newPost = document.createElement("div");
      newPost.className = "default-post";

      // Post picture
      let postPicture = document.createElement("div");
      postPicture.className = "post-picture";
      let profileImg = document.createElement("img");
      profileImg.src = "pictures/profile.png";
      profileImg.alt = "profile-img";
      profileImg.className = "profile-avatar";
      postPicture.appendChild(profileImg);
      newPost.appendChild(postPicture);

      // Post text
      let postTextDiv = document.createElement("div");
      postTextDiv.className = "post-text";
      postTextDiv.style.width = "100%";
      let postNameUsername = document.createElement("div");
      postNameUsername.className = "post-name-username";
      let nameUsernameP = document.createElement("p");
      nameUsernameP.innerHTML =
        "Nitesh Gupta &nbsp;&nbsp;<span class='post-username'> @nit_hck  . 1s</span>";
      postNameUsername.appendChild(nameUsernameP);
      postTextDiv.appendChild(postNameUsername);

      // Tweet text
      let tweetText = document.createElement("div");
      tweetText.className = "tweet-text";
      let textP = document.createElement("p");
      textP.className = "p-text-tweet";
      textP.innerHTML = postText;
      tweetText.appendChild(textP);
      postTextDiv.appendChild(tweetText);

      // Like and comment section
      let likeComment = document.createElement("div");
      likeComment.className = "like-comment";
      likeComment.innerHTML = `<div class="all-icons">
      <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi like-icons"><g><path d="M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756z"></path></g></svg>
      <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi like-icons"><g><path d="M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z"></path></g></svg>
      <svg id="svg2" onclick="toggleSVGs()" viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M20.884 13.19c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>
                          <svg id="svg1" onclick="toggleSVGs()" viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi"><g><path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>  
                          <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi like-icons"><g><path d="M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z"></path></g></svg>
                            <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi like-icons"><g><path d="M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11z"></path></g></svg>
                            </div>
                            <div class="upvote">
                            <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi like-icons"><g><path d="M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2z"></path></g></svg>
                            </div>`;
      // Upvote icon
      postTextDiv.appendChild(likeComment);

      // Like and comment section

      // Append the new post to the posts container
      newPost.appendChild(postTextDiv);
      var postsContainer = document.getElementById("postContainer");
      postsContainer.appendChild(newPost);

      // Clear the input field after posting
      document.getElementById("postInput").value = "";
    }
  }

  function toggleSVGs() {
    var svg1 = document.getElementById("svg1");
    var svg2 = document.getElementById("svg2");

    // Toggle the visibility of the SVG elements
    if (svg1.style.display === "none") {
      svg1.style.display = "block";
      svg2.style.display = "none";
    } else {
      svg1.style.display = "none";
      svg2.style.display = "block";
    }
  }

  // Add an event listener to the upvote icon
  var upvoteIcon = document.querySelector(".upvote svg");
  upvoteIcon.addEventListener("click", toggleSVGs);
});
function login() {
  console.log("here")
  document.getElementsByClassName("login-page")[0].style.display = "none";
  document.getElementsByClassName("main-section")[0].style.display = "block";
  // window.location.href="mobile-tweet.html"
}

function message() {
  document.getElementsByClassName("post-area")[0].style.display = "none";
  document.getElementsByClassName("message-section")[0].style.display = "flex";
}

function clickOnUser() {
  var users = document.getElementsByClassName("user");

  for (var i = 0; i < users.length; i++) {
    users[i].style.backgroundColor = "#484747";

    // Assuming you want to apply styles to child elements of each user
    var userChat = users[i].getElementsByClassName("user-chat")[0];
    var h4Elements = users[i].getElementsByTagName("h4");
    var spanElements = users[i].getElementsByTagName("span");

    userChat.style.backgroundColor = "#484747";

    for (var j = 0; j < h4Elements.length; j++) {
      h4Elements[j].style.backgroundColor = "#484747";
    }

    for (var k = 0; k < spanElements.length; k++) {
      spanElements[k].style.backgroundColor = "#484747";
      spanElements[k].style.color = "grey"; // Example of applying styles to span
    }
  }
}


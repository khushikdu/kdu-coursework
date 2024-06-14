const { v4: uuidv4 } = require('uuid');

class User {
    constructor() {
        this.users = [
            { id: uuidv4(), user_name: 'user1', user_email_id: 'user1@example.com', password: 'password1', profile_url: '' },
            { id: uuidv4(), user_name: 'user2', user_email_id: 'user2@example.com', password: 'password2', profile_url: '' },
        ];
    }

    getUserById(id) {
        return this.users.find(user => user.id === id);
    }

    getAllUsers() {
        return this.users;
    }

    postUser(newUser) {
        newUser.id = uuidv4();
        this.users.push(newUser);
        return newUser;
    }

    verifyUser(username, password) {
        console.log(username);
        console.log(password);
    
        let foundUser = null;
        this.users.forEach(user => {
            console.log("User data:", user);
            if (user.user_name === username && user.password === password) {
                console.log("User found:", user);
                foundUser = user;
            }
        });
    
        return foundUser;
    }    
}

module.exports = User;

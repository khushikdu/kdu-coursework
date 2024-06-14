const User = require('./user');
const userObj = new User();

function loginUser(req, res) {
    const { username, password } = req.body;
    console.log("fetching username and password");
    const user = userObj.verifyUser(username, password);
    console.log("user verified");
    if (user) {
        res.status(200).json({ auth: true, user });
    } else {
        console.log(user);
        res.status(401).json({ auth: false, message: 'Invalid username or password' });
    }
}

module.exports = { loginUser };

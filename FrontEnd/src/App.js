import React, { Component } from "react"
import "./App.css";
import facade from "./apiFacade";
import Home from "./components/Home";
import Courses from "./components/cources";
import {
  BrowserRouter as Router,
  Route,
  NavLink,
  Switch 
} from "react-router-dom";
class LogIn extends Component {
 constructor(props) {
   super(props);
   this.state = { username: "", password: "" }
 }
 login = (evt) => {
   evt.preventDefault();
   this.props.login(this.state.username, this.state.password);
 }
 onChange = (evt) => {
   this.setState({[evt.target.id]: evt.target.value})
 }
 render() {
   return (
     <div>
       <h2>Login</h2>
       <form onSubmit={this.login} onChange={this.onChange} >
         <input placeholder="User Name" id="username" />
         <input placeholder="Password" id="password" />
         <button>Login</button>
       </form>
     </div>
   )
 }
}
class LoggedIn extends Component {
 constructor(props) {
   super(props);
   this.state= {dataFromServer: "Fetching!!"};
 }
 componentDidMount(){
   facade.fetchData().then(res=> this.setState({dataFromServer: res.msg}));}
 render() {
   return (
     <div>
       <Header/>
       
       <Content/>
     </div>
   )
 }
}
class App extends Component {
 constructor(props) {
   super(props);
   this.state = { loggedIn: false }
 }
 logout = () => {facade.logout();
  this.setState({ loggedIn: false });}
 login = (user, pass) => {
  facade.login(user,pass)
  .then(res =>this.setState({ loggedIn: true }));
 }
 render() {
   return (
     <div>
       {!this.state.loggedIn ? (<LogIn login={this.login} />) :
         ( <div>
             <LoggedIn/>
             <button onClick={this.logout}>Logout</button>
           </div>)}
     </div>
   )
 }
}
export default App;
const Header = () => {
  return (
    <ul className="header">
      <Router><li>
        <NavLink exact activeClassName="active" to="/">
          Home
        </NavLink>
      </li>
      <li>
        <NavLink activeClassName="active" to="/Cources">
          Cources
        </NavLink>
      </li>
      </Router>
    </ul>
  );
};
const Content = () => {
  return (
    <Router>
    <Switch>
      <Route exact path="/">
        {" "}
        <Home />{" "}
      </Route>
      <Route path="/Cources">
        {" "}
        <Courses />{" "}
        </Route>
    </Switch>
    </Router>
  );
};
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Link} from "react-router-dom";
import "./Header.css"


const Header = () => {

  

    
    

    
    return ( 
        <>
        <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand to="/"><strong>Sunbase Task</strong></Navbar.Brand>
          <Nav className="ml-auto">
            <Nav.Link as={Link} to="/">Customers</Nav.Link>
            <Nav.Link as={Link} to="/addCust">Add Customer</Nav.Link>
            
            
          </Nav>
        </Container>
      </Navbar>
      

      
        
        
        </>
     );
}
 
export default Header;
import { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';
import axios from 'axios'
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
// import { useParams } from 'react-router-dom';


const Dash = () => {

    const [users,setUsers] = useState([]);

    const nav = useNavigate();
    

    useEffect(()=>{
        loadusers();
    },[])

    const loadusers = async ()=>{
        setUsers([]);
        try{
            const res = await axios.get("http://localhost:8080/api/customer");
        console.log(res.data);
        setUsers(res.data);
        }
        catch(e){
            console.log(e);
        }
        
    }

    const deleteusers = async (id)=> {
        try{
            await axios.delete(`http://localhost:8080/api/customer/${id}`);
            
            loadusers();
        }
        catch(e){
            console.log(e);
        }
    }

    const handleEdit = (id)=>{
        nav(`/editCust/${id}`);
    }

    //FETCH request for SYNC LOGIN PASSWORD DATA TO BE ADDED
    const fetchToken = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/token', {
                login_id : "",
                password :""
            });
  
            const ans = response.json();
            
            console.log(ans);
            nav('/');
        } catch (error) {
            console.error('Error fetching token:', error);
        }
    };



    return ( 
        <>
        <div className='my-4 mx-4 shadow bg-white rounded'>
        <Button onClick={() => {fetchToken()}} variant='light'>Sync</Button>
        <Table responsive="md">
        <thead>
          <tr>
            <th>#</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Street</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>

            {
                users.map((user,index) => (
                    <tr>
                        <th key={index}>{index+1}</th>
                        <td>{user.first_name}</td>
                        <td>{user.last_name}</td>
                        <td>{user.street}</td>
                        <td>{user.address}</td>
                        <td>{user.city}</td>
                        <td>{user.state}</td>
                        <td>{user.email}</td>
                        <td>{user.phone}</td>
                        <td className=''>
                            <Button classname="mx-1" variant='primary' onClick={() =>{nav(`/customer/${user.id}`)}}>View</Button>
                            <Button className="mx-1" variant="outline-dark" onClick={() => {handleEdit(user.id)}}>Edit</Button> 
                            <Button variant="danger" className='mx-1' onClick={() =>{deleteusers(user.id)}}>Delete</Button >
                        </td>
                    </tr>
                ))
            }
          
        </tbody>
        </Table>
        </div>
        
        
        </>
     );
}
 
export default Dash;
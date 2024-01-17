import { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';
import axios from 'axios'
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
// import { useParams } from 'react-router-dom';
import Form from 'react-bootstrap/Form';


import Pagination from 'react-bootstrap/Pagination';




const Dash = () => {

    // const [users,setUsers] = useState([]);  modify code for pagination

    const nav = useNavigate();

    //Pagination Code 
    const [page,setPage] = useState(0);
    const [users,setUsers] = useState([]);
    const [total_rec,setTotal_rec] = useState(0);
    const [total,setTotal] = useState(0);
    const [pagesize,setPageSize] = useState();
    const [sortBy,setSortBy] = useState();
    const [search,setSearch] = useState();
    const [search2,setSearch2] = useState("");

    


    
    








    let items = [];
    
    if(page >0){
        items.push(<Pagination.Prev key="prev" onClick={() =>setPage(page-1)} />)
    }
    
    for (let number = 0; number < total; number++) {
        items.push(
            <Pagination.Item key={number} data-page={number} active={number === page} onClick={() => setPage(number)}>
            {number}
            </Pagination.Item>
        );
    }
    if(page < total){
        items.push(<Pagination.Next key="next" onClick={() => setPage(page+1)} />)
    }
    

    useEffect(()=>{
        loadusers();
    },[page,sortBy,pagesize]);

    const loadusers = async ()=>{
        

        //pagination change
        try{
            const res = await axios.get("http://localhost:8080/api/customer",{params:{pageNumber:page,pageSize:pagesize}});
            console.log(res.data);
            setUsers(res.data.data);
            setTotal(res.data.totalPages);
            setTotal_rec(res.data.totalElements);
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

    //FETCH request for SYNC --> 
    //ADD login_id , Password 
    const fetchToken = async () => {

        
        
        try {
            const response = await axios.post('http://localhost:8080/api/token', {
                login_id :"",
                password :""
            });
  
            const ans = response.json();
            
            console.log(ans);
            
            window.location.reload('/');
        } catch (error) {
            console.error('Error fetching token:', error);
        }
    };
    
    const handle_page = (event)=> {
        
        const {value} = event.target;
        setPageSize(value);

    }
    const handle_sort = (event)=> {
        
        const {value} = event.target;
        setSortBy(value)

    }
    const handle_search = (event)=> {
        
        const {value} = event.target;
        setSearch(value)

    }

    const handle_sub = (e)=>{
        e.preventDefault();



    }




    return ( 
        <>
        <div className='my-4 mx-4 shadow bg-white rounded'>
        <Button onClick={() => {fetchToken()}} variant='dark'>Sync</Button>

        <h3 className='p-4 text-center'>Total Records : {total_rec}</h3>

        <div className=' justify-content-center align-items-center d-flex my-3 mx-4 gap-5'>
        
        

        
        
        <Form.Select id="pagesize" onChange={handle_page}>
        <option>Page Size</option>
        <option value={1}>1</option>
        <option value={5}>5</option>
        <option value={10}>10</option>
        <option value={20}>20</option>
        </Form.Select>

        <Form.Select onChange={handle_sort}>
        <option>Sort By</option>
        <option value="State">State</option>
        <option value="City">City</option>
        
        </Form.Select>

        <Form.Select onChange={handle_search}>
        <option>Search By</option>
        <option value="first_name">First Name</option>
        <option value="city">City</option>
        <option value ="email">Email</option>
        <option value="phone">Phone</option>
        
        
        </Form.Select>

        <Form onSubmit={handle_sub}>
            
            <Form.Control
                type="text"
                className='w-80'
                id="search"
                value={search2}
                onChange={(e) => setSearch2(e.target.value)} 
                
            />
        <Button className="w-20 h-20 m-2" type="submit">Search</Button>
        </Form>
        
        




        </div>
        

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

        {
            total > 0 && (
                <Pagination>{items}</Pagination>
            )
        }
        </div>
        
        
        </>
     );
        }
 
export default Dash;
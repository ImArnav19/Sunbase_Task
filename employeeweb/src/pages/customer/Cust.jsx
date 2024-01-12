import { useParams } from "react-router-dom";
import Table from 'react-bootstrap/Table';

import { useEffect,useState } from "react";


const Cust = () => {

    const {id} = useParams();

    const [data,setData] = useState({
        first_name:"",
        last_name:"",
        street:"",
        address:"",
        city:"",
        state:"",
        email: "",
        phone: ""
        
    })

    
        useEffect(()=>{
            getUser();
        })
    const getUser = async() =>{
        try{
            const response = await fetch(`http://localhost:8080/api/customer/${id}`,{
                    method:"GET",
                    headers:{"Content-Type":"application/json"},
                    
                });
            setData(await response.json());

        }
        catch(e){
            console.log(e)
        }
    }
    
    
    return ( 
            <>
            <div className="shadow w-50vw">
            <h1 className="text-">Customer ID : {id}</h1>

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
                    
                    </tr>
                    </thead>

                    
                    <tbody>
                    <tr>
                    <th>1</th>
                    <td>{data.first_name}</td>
                    <th>{data.last_name}</th>
                    <th>{data.street}</th>
                    <th>{data.address}</th>
                    <th>{data.city}</th>
                    <th>{data.state}</th>
                    <th>{data.email}</th>
                    <th>{data.phone}</th>
                    
                    </tr>
                    </tbody>
                    </Table>
            </div>
            

            </>
     );
}
 
export default Cust;
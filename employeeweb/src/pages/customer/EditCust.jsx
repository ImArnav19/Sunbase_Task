import Button from 'react-bootstrap/Button';
import { useState } from "react";
import "./cust.css"

import Form from "react-bootstrap/Form";
import { useNavigate, useParams } from 'react-router-dom';

const EditCust = () => {

    const [formData,SetformData] = useState({
        first_name:"",
        last_name:"",
        street:"",
        address:"",
        city:"",
        state:"",
        email: "",
        phone: ""
        
    })

    const navigate = useNavigate();

    const handleChange = (event)=> {
        
        const {name,value} = event.target;
        SetformData({
            ...formData,
            [name]:value,
        })

    }

    const {id} = useParams();

        const handleSubmit = async (e)=> {
            e.preventDefault();
            
            try{
                const response = await fetch(`http://localhost:8080/api/customer/${id}`,{
                    method:"PUT",
                    headers:{"Content-Type":"application/json"},
                    body:JSON.stringify(formData)
                });
    
                const data = await response.json();
                console.log("Customer Updated! :",data);
                navigate('/');
    
            }
            catch(e){
                console.log("Error occured ",e);
            }
        }

        
        
        

    return ( 
        <>
            <div className="center-form">
            <h1 className=''>Update Customer</h1>
            <Form className='ok' onSubmit={handleSubmit}>
                <Form.Group controlId="formBasicEmail">
                    <Form.Control 
                        type="text"
                        name="first_name"
                        value={formData.first_name}
                        placeholder="Enter First Name"
                        onChange={handleChange}
                    
                    />

                </Form.Group>
                <Form.Group controlId="formBasicName">
                    <Form.Control 
                        type="text"
                        name="last_name"
                        value={formData.last_name}
                        placeholder="last name"
                        onChange={handleChange}
                    
                    />

                </Form.Group>

                

                <Form.Group controlId="formBasicName">
                    <Form.Control 
                        type="text"
                        name="street"
                        value={formData.street}
                        placeholder="street"
                        onChange={handleChange}
                    
                    />

                </Form.Group>

                <Form.Group controlId="formBasicName">
                    <Form.Control 
                        type="text"
                        name="address"
                        value={formData.address}
                        placeholder="address"
                        onChange={handleChange}
                    
                    />

                </Form.Group>

                <Form.Group controlId="formBasicName">
                    <Form.Control 
                        type="text"
                        name="city"
                        value={formData.city}
                        placeholder="city"
                        onChange={handleChange}
                    
                    />

                </Form.Group>

                <Form.Group controlId="formBasicName">
                    <Form.Control 
                        type="text"
                        name="state"
                        value={formData.state}
                        placeholder="state"
                        onChange={handleChange}
                    
                    />

                </Form.Group>

                <Form.Group controlId="formBasicName">
                    <Form.Control 
                        type="text"
                        name="email"
                        value={formData.email}
                        placeholder="email"
                        onChange={handleChange}
                    
                    />

                </Form.Group>

                <Form.Group controlId="formBasicName">
                    <Form.Control 
                        type="text"
                        name="phone"
                        value={formData.phone}
                        placeholder="Phone"
                        onChange={handleChange}
                    
                    />

                </Form.Group>

                <Button  variant="primary" className='w-100 m-10' type="submit">Update Customer</Button>
            </Form>
            

            

            </div>
        
        </>
     );
}
 
export default EditCust;
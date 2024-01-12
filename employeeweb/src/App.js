
import './App.css';
import Header from './pages/header/Header';
import { Route, Routes } from 'react-router-dom';
import Dash from './pages/dashboard/Dash';
import Nomatch from './pages/NoMatch/Nomatch';
import AddCust from './pages/customer/AddCust';
import EditCust from './pages/customer/EditCust';
import Cust from './pages/customer/Cust';

function App() {
  return (<>
    <Header />

    <Routes>
      <Route path='/' element={<Dash/>}/>
      <Route path="/addCust" element={<AddCust/>} />
      <Route path="/editCust/:id" element={<EditCust/>} />
      <Route path="/customer/:id" element={<Cust/>} />
      <Route path='*' element={<Nomatch/>} />
    </Routes>
    
  
  </>
  )
    
}

export default App;

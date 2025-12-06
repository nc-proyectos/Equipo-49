import PeopleIcon from '@mui/icons-material/People';
import NearMeIcon from '@mui/icons-material/NearMe';
import ShowChartIcon from '@mui/icons-material/ShowChart';
import WatchLaterIcon from '@mui/icons-material/WatchLater';
import "./dashboard.css"
import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';
//import Box from '@mui/material/Box';
import  { DataGrid }  from '@mui/x-data-grid';
import AddIcon from '@mui/icons-material/Add';
import WhatsAppIcon from '@mui/icons-material/WhatsApp';
import EmailIcon from '@mui/icons-material/Email';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import { BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid, ResponsiveContainer } from "recharts";

const data = [
  { name: "WhatsApp", value: 4200 },
  { name: "Email", value: 2900 },
  { name: "Llamadas", value: 1100 },
  { name: "SMS", value: 200 },
];



//CHARTS



export default function Dashboard() {

  const [tickPlacement, setTickPlacement] = React.useState('middle');
  const [tickLabelPlacement, setTickLabelPlacement] = React.useState('middle');

  
  const [age, setAge] = React.useState('');

  const handleChange = (event) => {
    setAge(event.target.value);
  };
  

  return (
    <>
      <h2>Hola,Camila!</h2>
      <div className='informacion'>
        <article className='cuadro'>
          <div className='porcentaje'>
            <PeopleIcon/>
            <p>+0%</p>
          </div>
          <p>Contactos Activos</p>
          <h2>0</h2>
          <p>0 leads, 0 clientes</p>
        </article>
        <article className='cuadro'>
          <div className='porcentaje'>
            <NearMeIcon/>
            <p>+0%</p>
          </div>
        <p>Mensajes Enviados</p>
        <h2>0</h2>
        <p>Esta semana</p>
      </article>
        <article className='cuadro'>
          <div className='porcentaje'>
            <ShowChartIcon/>
            <p>+0%</p>
          </div>
          <p>Tasa de Respuesta</p>
          <h2>0%</h2>
          <p>Promedio mensual</p>
        </article>
        <article className='cuadro'>
          <div className='porcentaje1'>
            <WatchLaterIcon/>
            <p>Pendientes</p>
          </div>
          <p>Seguimiento Hoy</p>
          <h2>0</h2>
          <p>0 urgentes, 0 normales</p>
        </article>
      </div>
    <div style={{display:'flex'}}>  
      <div style={{ width: "50%", height: 306 , marginTop: '5%'}}>
      <h3>Mensajes por Canal</h3>

      <ResponsiveContainer width="100%" height="100%">
        <BarChart data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Bar dataKey="value" fill="#4ade80" /> {/* Color verde (WhatsApp style) */}
        </BarChart>
      </ResponsiveContainer>
    </div>   
    <div style={{border: 'groove' , borderRadius: '2%', marginTop:'10%' , width: '30%' , marginLeft: '10%' , height:'1%'}}>
      <span style={{fontWeight: 'bold' , textAlign: 'Center'}}>Acciones Rapidas</span>
      <div style={{display: 'flex' , marginTop:'5%'}}>
        <AddIcon style={{background:'#8CD3EC' , borderRadius: '5%' }}/><p>Nuevo lead</p><ArrowForwardIosIcon style={{marginLeft:'69%'}}/>
      </div>
      <div style={{display: 'flex'}}>
        <WhatsAppIcon style={{background:'#38DA84', borderRadius: '5%'}}/><p>Enviar WhatsApp</p><ArrowForwardIosIcon style={{marginLeft:'60%'}}/>
      </div>
      <div style={{display: 'flex'}}>
        <EmailIcon style={{background:'#EF77ED' , borderRadius: '5%'}}/><p>Campaña Email</p><ArrowForwardIosIcon style={{marginLeft:'62%'}}/>
      </div>
    </div>
  </div>

      <div className='contenedorInformacion'>
        <div style={{ height: 250, width: '46%' , marginLeft: '5%', border: 'groove' , borderRadius: '2%' , marginTop: '12%' }}>
        <div
          style={{
            display:'flex',
            justifyContent: 'space-between',
            marginBottom:'8px',
            fontWeight: 'bold'
          }}>
            <p>Conversaciones</p>
            <span>Ver todos</span>
        </div>
        <DataGrid
          sx={{
              "& .MuiDataGrid-columnHeaders": {
              display: "none"
            }
          }}
          disableColumnMenu
          disableRowSelectionOnClick
          rows={[]}
          columns={[
          { 
          field: "a", 
          headerName: "Conversaciones", 
          width: 200,
          sortable: false,
          renderCell: () => "Recordatorios"
          },
          {field: "b", 
          headerName: "", 
          width: 150,
          sortable: false,
          renderCell: () => (
            <div style={{marginLeft: '60%'}}>
              Ver todos
            </div>
            ) 
          }
          ]}
          hideFooter
          localeText={{
            noRowsLabel: "No hay informacion disponible"
          }}
        />
      </div>
      <div style={{ height: 250, width: '30%' , marginLeft: '9%' , border: 'groove' , borderRadius: '2%' , marginTop: '12%' }}>
        <div
          style={{
            display:'flex',
            justifyContent: 'space-between',
            marginBottom:'8px',
            fontWeight: 'bold'
          }}>
            <p>Reuniones</p>
            <AddIcon/>
        </div>
        <DataGrid
          sx={{
              "& .MuiDataGrid-columnHeaders": {
              display: "none"
            }
          }}
          disableColumnMenu
          disableRowSelectionOnClick
          rows={[]}
          columns={[
        { 
          field: "a", 
          headerName: "Reuniones", 
          width: 200,
          sortable: false,
          renderCell: () => "Reuniones"
          },
          
        
          {field: "b", 
          headerName: "", 
          width: 95,
          marginLeft: 200,
          sortable: false,
          }
          
          ]}
          hideFooter
          localeText={{
            noRowsLabel: "No hay informacion disponible"
          }}
          />
        </div>
      </div>
    </>
  )
}

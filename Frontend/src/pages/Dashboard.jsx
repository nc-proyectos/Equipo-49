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

export default function Dashboard() {
  
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
          <h2>23</h2>
          <p>0 urgentes, 0 normales</p>
        </article>
      </div>
      <div className='dosCuadros'>
        <article className='dosCuadros1'>
          <h3>Titulo</h3>
          <Box className="icono1" sx={{ minWidth: 120 }}>
          <FormControl className='select' fullWidth>
          <InputLabel id="demo-simple-select-label">Age</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={age}
            label="Age"
            onChange={handleChange}
          >
            <MenuItem value={10}>Ten</MenuItem>
            <MenuItem value={20}>Twenty</MenuItem>
            <MenuItem value={30}>Thirty</MenuItem>
          </Select>
          </FormControl>
        </Box>
        </article>
        <article className='dosCuadros2'>
          <h3>Titulo</h3>
          <MoreHorizIcon className='icono2'/>
        </article>
      </div>
      <div className='contenedorInformacion'>
        <div style={{ height: 250, width: '46%' , marginLeft: '5%', border: 'groove' , borderRadius: '2%' }}>
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
      <div style={{ height: 250, width: '30%' , marginLeft: '9%' , border: 'groove' , borderRadius: '2%' }}>
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
/*
<div style= {{marginLeft: '88%'}}>
            <AddIcon/>
          </div>
/*
<div className='conversaciones'>
          <article>
            <div className='conversacionesRecientes'>
              <h5>Conversaciónes Recientes</h5>
              <h6>Ver Todas</h6>
            </div>
            <div>
              <h4>No hay conversaciónes todavía</h4>
            </div>
          </article>
          <article className='recordatorios'>
            <div className='recordatorios'>
              <h3>Recordatorios</h3>
              <AddIcon/>
            </div>
          </article>
        </div>
*/
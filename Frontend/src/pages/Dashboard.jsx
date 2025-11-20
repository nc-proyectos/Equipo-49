import PeopleIcon from '@mui/icons-material/People';
import NearMeIcon from '@mui/icons-material/NearMe';
import ShowChartIcon from '@mui/icons-material/ShowChart';
import WatchLaterIcon from '@mui/icons-material/WatchLater';

export default function Dashboard() {
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
          <div className='porcentaje'>
            <WatchLaterIcon/>
            <p>Pendientes</p>
          </div>
          <p>Seguimiento Hoy</p>
          <h2>23</h2>
          <p>0 urgentes, 0 normales</p>
        </article>
      </div>
    </>
  )
}

import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import DeleteIcon from '@mui/icons-material/Delete';
import { useState } from 'react';



export default function EnviarMensaje () {

    const [enviar , setEnviar] = useState(false);

    const handleClick = () => {
    
    setEnviar(true)
    
    }

    if(enviar) {
        return <h2 style={{textAlign: 'center' , marginTop: '20%' , fontWeight: 'bold'}}>Mensaje enviado</h2>
    }

    return (
        <>
            <div style={{marginTop: '2%'}} className="mb-3">
                <label for="exampleFormControlInput1" className="form-label">PARA</label><DeleteIcon/>
                <input type="email" className="form-control" id="exampleFormControlInput1" placeholder="name@example.com"></input>
            </div>
            <div className="mb-3">
                <label for="exampleFormControlTextarea1" className="form-label"></label>
                <textarea className="form-control" id="exampleFormControlTextarea1" rows="3" placeholder='Asunto'></textarea>
            </div>
            <div>
                <button onClick={handleClick} style={{backgroundColor: '#097DB3' , color:'white' , fontWeight:'bold' , border: 'none' , borderRadius: '8%'}} ><ArrowForwardIcon/>Enviar</button>
            </div>
        </>
    )
}

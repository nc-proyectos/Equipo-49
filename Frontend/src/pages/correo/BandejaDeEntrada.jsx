import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

function createData(name, calories, fat, carbs, protein) {
return { name, calories, fat, carbs, protein };
}

const users = [
createData(),
createData(),
createData(),
createData(),
createData(),
];

export default function BandejaDeEntrada () {
    return (
        <>  
            <TableContainer style={{marginTop: '5%'}} component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
        <TableRow>
            <TableCell>Users</TableCell>
            <TableCell align="right">@</TableCell>
        </TableRow>
        </TableHead>
        <TableBody>
        {users.map((row) => (
            <TableRow
            key={row.name}
            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
            <TableCell component="th" scope="row">
                {row.name}
            </TableCell>
            <TableCell align="right">{row.email}</TableCell>
            </TableRow>
        ))}
        </TableBody>
    </Table>
    </TableContainer>
        </>
    )
}
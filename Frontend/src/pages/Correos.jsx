import React from 'react'
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import AppBar from '@mui/material/AppBar';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import MailIcon from '@mui/icons-material/Mail';
import EmailIcon from '@mui/icons-material/Email';
import MoveToInboxIcon from '@mui/icons-material/MoveToInbox';
import { NavLink } from 'react-router-dom';

import { styled, alpha } from '@mui/material/styles';
import IconButton from '@mui/material/IconButton';
//import Typography from '@mui/material/Typography';
import InputBase from '@mui/material/InputBase';
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import "./correos.css";

const drawerWidth = 240;

const Search = styled('div')(({ theme }) => ({
  position: 'relative',
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  '&:hover': {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginLeft: 0,
  width: '100%',
  [theme.breakpoints.up('sm')]: {
    marginLeft: theme.spacing(1),
    width: 'auto',
  },
}));

const SearchIconWrapper = styled('div')(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: '100%',
  position: 'absolute',
  pointerEvents: 'none',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: 'inherit',
  width: '100%',
  '& .MuiInputBase-input': {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create('width'),
    [theme.breakpoints.up('sm')]: {
      width: '12ch',
      '&:focus': {
        width: '20ch',
      },
    },
  },
}));


export default function Correos() {
  return (
    <>
      <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar style={{backgroundColor: 'dodgerblue'}} position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
      </AppBar>
        <Toolbar />
        <Box sx={{ overflow: 'auto' }}>
          <div className='logoEmail'>
            <h1>Outlook</h1>
          </div>
          <List className='menuCorreo'>
            <NavLink className='navlink' to='/enviarMensaje'><MoveToInboxIcon/>Enviar mensajes</NavLink>
            <NavLink className='navlink' to='/bandejaDeEntrada'><EmailIcon/>Bandeja de Entrada</NavLink>
            <NavLink className='navlink' to='/correoNoDeseado'><MoveToInboxIcon/>Correo no deseado</NavLink>
            <NavLink className='navlink' to='/ElementosEliminados'><EmailIcon/>Elementos eliminados</NavLink>
          </List>
          <Divider />
        </Box>
      </Box>
      <Box>
      <Box sx={{ flexGrow: 1, display: 'flex', flexDirection: 'column' }}>
        {/* Barra superior del contenido */}
        <Box sx={{ display: 'flex', alignItems: 'center', p: 2, borderBottom: '1px solid #ccc' }}>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>
            Bandeja de entrada
          </Typography>
          <Box sx={{ display: 'flex', alignItems: 'center', border: '1px solid #ccc', borderRadius: 1, p: '0 8px' }}>
            <SearchIcon />
            <InputBase placeholder="Buscar…" sx={{ ml: 1 }} />
          </Box>
        </Box>
      </Box>
      </Box>
    </>
  )
}

/*
{['Enviar mensajes', 'Bandeja de entrada', 'Correo no deseado', 'Elementos eliminados'].map((text, index) => (
              <ListItem key={text} disablePadding>
                <ListItemButton>
                  <ListItemIcon>
                    {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
                  </ListItemIcon>
                  <ListItemText primary={text} />
                </ListItemButton>
              </ListItem>
            ))}

<AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="open drawer"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } }}
          >
            MUI
          </Typography>
          <Search>
            <SearchIconWrapper>
              <SearchIcon />
            </SearchIconWrapper>
            <StyledInputBase
              placeholder="Search…"
              inputProps={{ 'aria-label': 'search' }}
            />
          </Search>
        </Toolbar>
      </AppBar>
    </Box>
  */
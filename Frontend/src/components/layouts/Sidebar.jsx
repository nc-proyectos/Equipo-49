import { Link } from "react-router-dom";
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import CssBaseline from '@mui/material/CssBaseline';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import MailIcon from '@mui/icons-material/Mail';
import WhatsAppIcon from '@mui/icons-material/WhatsApp';
import DashboardIcon from '@mui/icons-material/Dashboard';
import FilterAltSharpIcon from '@mui/icons-material/FilterAltSharp';
import WatchLaterSharpIcon from '@mui/icons-material/WatchLaterSharp';
import SmartToySharpIcon from '@mui/icons-material/SmartToySharp';
import PeopleAltSharpIcon from '@mui/icons-material/PeopleAltSharp';
import LabelSharpIcon from '@mui/icons-material/LabelSharp';
import SimCardDownloadIcon from '@mui/icons-material/SimCardDownload';
import SettingsRoundedIcon from '@mui/icons-material/SettingsRounded';
import SearchIcon from '@mui/icons-material/Search'
import { styled, alpha } from '@mui/material/styles';
import InputBase from '@mui/material/InputBase';


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

export default function Sidebar() {
  return (
    <>
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar
        position="fixed"
        sx={{ width: `calc(100% - ${drawerWidth}px)`, ml: `${drawerWidth}px` }}
      >
        <Toolbar className="inicio">
          <Typography variant="h6" noWrap component="div">
            Hola, Camila!
          </Typography>
          <Search className="search">
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
      <Drawer
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          '& .MuiDrawer-paper': {
            width: drawerWidth,
            boxSizing: 'border-box',
          },
        }}
        variant="permanent"
        anchor="left"
      >
      <Toolbar/>
      <Divider/>
        <List className="enlaces">
          <Link to="/" className="nav-item"><DashboardIcon className="icono"/>Dashboard</Link>
          <Link to="/contactos" className="nav-item"><PeopleAltSharpIcon className="icono"/>Contactos</Link>
          <Link to="/whatsapp" className="nav-item"><WhatsAppIcon className="icono"/>Whatsapp</Link>
          <Link to="/correos" className="nav-item"><MailIcon className="icono"/>Correos</Link>
          <Link to="/segmentacion" className="nav-item"><FilterAltSharpIcon className="icono"/>Segmentación</Link>
          <Link to="/recordatorios" className="nav-item"><WatchLaterSharpIcon className="icono"/>Recordatorios</Link>
          <Link to="/analiticas" className="nav-item"><SmartToySharpIcon className="icono"/>Analíticas</Link>
          </List>
        <Divider />
        <List className="enlaces1">
          <p>Configuracion</p>
          <Link to="/etiquetas" className="nav-item1"><LabelSharpIcon className="icono2"/>Etiquetas</Link>
          <Link to="/exportarDatos" className="nav-item1"><SimCardDownloadIcon className="icono2"/>Exportar Datos</Link>
          <Link to="/ajustes" className="nav-item1"><SettingsRoundedIcon className="icono2"/>Ajustes</Link>
        </List>
      </Drawer>
      <Box
        component="main"
        sx={{ flexGrow: 1, bgcolor: 'background.default', p: 3 }}
      >
      </Box>
    </Box>
    </>  
    );
}



/*
<CssBaseline />
      <AppBar
        position="fixed"
        sx={{ width: `calc(100% - ${drawerWidth}px)`, ml: `${drawerWidth}px` }}
      >
        <Toolbar className="inicio">
          <Typography variant="h6" noWrap component="div">
            Hola, Camila!
          </Typography>

        </Toolbar>
      </AppBar>
        <Toolbar />
        <Divider />
*/

/*

{['Inbox', 'Starred', 'Send email', 'Drafts'].map((text, index) => (
            <ListItem key={text} disablePadding>
              <ListItemButton>
                <ListItemIcon>
                  {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
                </ListItemIcon>
                <ListItemText primary={text} />
              </ListItemButton>
            </ListItem>
          ))}
*/






















/*
<div className="sidebar">
      <h2>CRM</h2>
      <nav>
        <Link to="/" className="nav-item">Dashboard</Link>
        <Link to="/contactos" className="nav-item">Contactos</Link>
        <Link to="/whatsapp" className="nav-item">Whatsapp</Link>
        <Link to="/correos" className="nav-item">Correos</Link>
        <Link to="/segmentacion" className="nav-item">Segmentación</Link>
        <Link to="/recordatorios" className="nav-item">Recordatorios</Link>
        <Link to="/analiticas" className="nav-item">Analíticas</Link>
      </nav>
    </div>
  
*/
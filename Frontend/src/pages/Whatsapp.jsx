import React from "react";
import Chat from "../components/Chat/Chat"


function Whatsapp() {
  return (
    <div className="page-container" style={{ padding: "2rem" }}>
      <h2>Whatsapp</h2>
      <div className="placeholder-content">
     
        <Chat/>
      </div>
    </div>
  );
}

export default Whatsapp;

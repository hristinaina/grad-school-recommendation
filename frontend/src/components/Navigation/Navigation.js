import React from "react";
import { Navbar, NavItem, NavLink } from "reactstrap";
import { Link, useLocation } from "react-router-dom";
import "./Navigation.css";

const Navigation = () => {
  const location = useLocation();

  return (
    <header>
      <Navbar className="navbar">
        <ul>
          <img src="/logo-white.png" className="logo" alt="Logo" />
          <NavItem>
            <NavLink
              tag={Link}
              className={`text-light ${location.pathname === '/programs' ? 'active' : ''}`}
              to="/programs"
            >
              Programs
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink
              tag={Link}
              className={`text-light ${location.pathname === '/recommendation' ? 'active' : ''}`}
              to="/recommendation"
            >
              Recommendation
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink
              tag={Link}
              className={`text-light ${location.pathname === '/mentorship' ? 'active' : ''}`}
              to="/mentorship"
            >
              Mentorship
            </NavLink>
          </NavItem>
          <NavItem className="logout">
            <NavLink tag={Link} className="text-light" to="/">
              Log out
            </NavLink>
          </NavItem>
        </ul>
      </Navbar>
    </header>
  );
};

export default Navigation;

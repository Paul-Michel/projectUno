import React, { Component } from 'react';
import './css/materialize.min.css';
import './footer.css';

class Footer extends Component {

    render() {

        return (
            <div class="container">
                <style>
                    @import url('https://fonts.googleapis.com/icon?family=Material+Icons'),
                </style>
                <footer class="animated fadeInUp page-footer  colorFoot">
                    <div class="footer-copyright ">
                        <div class="container">
                            © 2018 Copyright Uno
                        </div>
                    </div>
                </footer>
            </div>
        )
    }
}
export default Footer;
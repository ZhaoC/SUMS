/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function chooseClub(){
    document.getElementById("clubName").setAttribute("value", "#{clubMB.getSelectedClubName()}");
    document.getElementById("clubNote").setAttribute("value", "#{clubMB.getSelectedClubNote()}");
}

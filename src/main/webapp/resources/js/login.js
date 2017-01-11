$(document).ready(function(){
    var classCycle=['backgroud1','backgroud2','backgroud3', 'backgroud4', 'backgroud5'];

    var randomNumber = Math.floor(Math.random() * classCycle.length);
    var classToAdd = classCycle[randomNumber];

    $('body').addClass(classToAdd);
});
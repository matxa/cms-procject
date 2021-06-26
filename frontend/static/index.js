const $ = window.$;

// SET TIMER FOR FLASHED MESSAGES
setTimeout(function() {
    $('.flash-error').hide()
    $('.flash-success').hide()
}, 5000);

// COMFIRM IF USER WANTS TO DELETE CARD
$('.confirm').hide()
$('#login-btn').click(function(){
    $(this).hide()
    $('.confirm').show()
    $('.btn-no').click(function(){
        $('.confirm').hide()
        $('#login-btn').show()
    });

});

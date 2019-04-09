$(document).ready(function() {
    $("#subscriptionCount").on("click", function () {
         $.ajax({
            url: "/subscriptionList",
            method: "POST"
        });
    });
});

// $(document).on('click', '#subscriptionCount', function () {
//     populateSubscription();
// });
// var populateSubscription = function () {
//     var subs = $.ajax({
//         url: "/subscriptionList",
//         method: "POST"
//     });
//     subs.done(function (data) {
//         if (data) {
//             data.forEach(function (sub) {
//                 $('#subList').append("<li>" + sub.name + "</li>");
//             });
//         }
//     });
//     subs.fail(function (jqXHR, textStatus) {
//         console.log("Error in fetching users");
//     })
// };



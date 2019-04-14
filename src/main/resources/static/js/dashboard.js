
// $(document).ready(function () {
//         var link = $(this).attr("link");
//         // $("#downloadLink").toggle(link==null);
//         $("#fullSiteLink").toggle(link!=null);
//     if (link==null) {
//         $("#downloadLink").show();
//     } else {
//         $("#downloadLink").hide();
//     }
// });




$(document).ready(function () {

    $("#editTextBox").hide();
    $("#editButton").hide();

    $("#unsubscribe").on("click",function () {
        var topicId = $(this).attr("topic-id");
        $.ajax({
            url: "/unsubscribe",
            method: "POST",
            data: {topicId: topicId},
            success: function () {
                location.reload(true);
            }
        });
    });

    $("#markAsRead").on("click",function (event) {
        event.preventDefault();
        var resourceId = $(this).attr("resource-id");
        $.ajax({
            url: "/markAsRead",
            method: "post",
            data: { resourceId : resourceId },
            success: function () {
                location.reload(true);
            }
        });
    });


    $('select[id="visibility"]').on('change',function () {
        var visibility = $(this).val();
        var topicId = $(this).attr("topicId");
        $.ajax({
            url: "/setVisibility",
            method: "post",
            data: { topicId: topicId, visibility: visibility},
            success: function () {
                alert("Visibility changed!!");
            }
        })
    });

    $('select[id="seriousness"]').on('change', function () {
        var seriousness = $(this).val();
        var username = $(this).attr("username");
        var topicId = $(this).attr("topicId");
        alert(seriousness);
        alert(username);
        alert(topicId);
        $.ajax({
            url: "/setSeriousness",
            method: "post",
            data: { topicId: topicId, username: username, seriousness: seriousness},
            success: function(){
                alert("Seriousness changed");
            }
        })
    });


    $('select[id="topic"]').on('change', function(){
        var email = $("#email").val();
        var topic = $(this).val();
        $("#invite").on('click', function () {
            alert("Sending invite");
            $.ajax({
                url: "sendInvite",
                method: "post",
                data: { email:email, topic:topic },
                success: function () {
                    alert("Email sent successfully!!");
                }
            })
        })
    });

    $("#deleteTopic").on('click', function () {
        alert("deleting");
        var topicId = $(this).attr("topicId");
        alert(topicId);
        $.ajax({
            url: "deleteTopic",
            method: "post",
            data: { topicId: topicId },
            success: function () {
                alert("Topic deleted successfully!!");
            }
        })
    });

    $("#editTopic").on('click', function () {
        $("#topicName").hide();
        $("#editTextBox").show();
        $("#editButton").show();

    });

    $("#editButton").on('click', function () {
        var oldName = $("#topicName").val();
        var newName = $("#editTextBox").val();
        $.ajax({
            url: "/editTopicName",
            method: "post",
            data: { oldName: oldName, newName: newName},
            success: function () {
                alert("Topic name changed!!");
                $("#topicName").show();
                $("#editTextBox").hide();
                $("#editButton").hide();
            }
        })

    })





});







// $(document).ready(function() {
//     $("#createTopicButton").on("click", function () {
//          $.ajax({
//              url: "/saveTopic",
//              method: "POST",
//              data: {name: name, visibility: visibility},
//              success: function (data) {
//                  alert("Topic created!!")
//              }
//          });
//     });
// });


// $(document).ready(function() {
//     $("#subscriptionCount").on("click", function () {
//          $.ajax({
//              url: "/subscriptionList",
//              method: "POST",
//              success: function (data) {
//                  console.log(data);
//                  $("#subscriptionList").html(data);
//              }
//          });
//     });
// });


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



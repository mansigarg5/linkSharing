
$(document).ready(function () {

    $("#unsubscribe").on("click",function () {
        var topicId = $(this).attr("topic-id");
        $.ajax({
            url: "/unsubscribe",
            method: "POST",
            data: {topicId: topicId},
            success: function () {
                alert("You unsubscribed the post!!");
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
                alert("The post is marked as read!!");
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
        var topicId = $(this).attr("topicId");
        $.ajax({
            url: "deleteTopic",
            method: "post",
            data: { topicId: topicId },
            success: function () {
                alert("Topic deleted successfully!!");
                location.reload(true);
            }
        })
    });

    $("#editTopic").on('click', function () {
        $("#topicName").hide();
        var x = document.getElementById("hideElement");
        x.style.display = "block";

    });

    $("#editButton").on('click', function () {
        var oldName = $(this).attr("oldName");
        var newName = $("#editTextBox").val();
        $.ajax({
            url: "/editTopicName",
            method: "post",
            data: { oldName: oldName, newName: newName},
            success: function () {
                alert("Topic name changed!!");
                location.reload(true);
            }
        })

    });

    $('select[id="rating"]').on('change', function () {
        var rating = $(this).val();
        var resourceId = $(this).attr("resourceId");
        $.ajax({
            url: "/setRating",
            method: "post",
            data: { resourceId: resourceId, rating: rating},
            success: function(){
                alert("Rating changed!!");
            }
        })
    });

    $("#editPost").on('click', function () {
        $("#description").hide();
        var post = document.getElementById("hidePost");
        post.style.display = "block";

    });

    $("#editPostButton").on('click', function () {
        var resourceId = $(this).attr("resourceId");
        var newName = $("#editPostTextBox").val();
        $.ajax({
            url: "/editPostDescription",
            method: "post",
            data: { resourceId: resourceId, newName: newName},
            success: function () {
                alert("Post description changed!!");
                location.reload(true);
            }
        })

    });

    $('select[id="activateUser"]').on('change', function () {
        var activate = $(this).val();
        var userId = $(this).attr("userId");
        $.ajax({
            url: "/activateUser",
            method: "post",
            data: { userId: userId, activate: activate},
            success: function(){
                alert("User activated/deactivated !!");
            }
        })
    });

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



/**
 * Ajax request for update Task.
 */
function updateTask(id, done) {
    $.ajax({
        method: "put",
        url: "./task",
        contentType: "application/json",
        data: JSON.stringify({
            id: id,
            done: done
        }),
        success: function() {
            showTableTask();
        }
    });
}

/**
 * Ajax request for adding Task.
 */
function addTask() {
    $.ajax({
        method: "post",
        url: "./task",
        contentType: "application/json",
        data: JSON.stringify({
            description: $('#description').val(),
            dueDate: $('#dueDate').val(),
        }),
        success: function() {
            showTableTask();
        }
    });
}

/**
 * Ajax request for get all or not done Task.
 */
function getTask(done) {
    $.ajax({
        url: "./task?done=" + done,
        success: data => {
            drawTableTasks(data);
        }
    });
}

/**
 * Ajax request for removing Task.
 */
function removeTask(id) {
    $.ajax({
        method: "DELETE",
        url: "./task",
        contentType: "application/json",
        data: JSON.stringify({
            id : id
        }),
        success: function() {
            showTableTask();
        }
    });
}
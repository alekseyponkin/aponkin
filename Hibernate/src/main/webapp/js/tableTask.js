/**
 * Toggle showing all or not done Task.
 */
function showTableTask() {
    if ($('#checkBoxShowNotDone').prop('checked')) {
        getTask("all");
    } else {
        getTask("notDone");
    }
}

/**
 * Draw table Tasks .
 */
function drawTableTasks(tasks) {
    $('#tableTask tr>td').remove();
    let numberCol = 1;
    let table = '';
    tasks = sortTasksByDate(tasks);
    $.each(tasks, (index, value) => {
        table = table +
            `<tr scope="row">
            <td scope="col" class="text-center align-middle">${numberCol++}</td>
            <td scope="col" class="text-center align-middle">${value.description}</td>
            <td scope="col" class="text-center align-middle">${value.dueDate}</td>
            <td scope="col">
            <div class="row justify-content-center">
            <input type="checkbox" class="check-input" ${(value.done ? "checked" : "")} id="doneTask" onclick="updateTask(${value.id}, this.checked)">
            </div>
            </td>
            <td scope="col">
            <div class="row justify-content-center">
            <button type="button" class="btn btn-success align-middle" onclick="removeTask(${value.id})">Remove</button>
            </div>
            </td>
            </tr>`;
    });
    $('#tableTask tr:last').after(table);
}

/**
 * Sort Tasks by date.
 */
function sortTasksByDate(listTasks) {
    return listTasks.sort((a, b) => {
        return (a.dueDate < b.dueDate) ? -1 : (a.dueDate > b.dueDate) ? 1 : 0
    });
}

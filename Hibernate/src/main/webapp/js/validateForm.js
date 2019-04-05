/**
 * Validate form.
 */
function validate() {
    if ($('#description').val() === '') {
        alert($('#description').prop('title'));
        return false;
    }
    if ($('#dueDate').val() === '') {
        alert($('#dueDate').prop('title'));
        return false;
    }
    return true;
}
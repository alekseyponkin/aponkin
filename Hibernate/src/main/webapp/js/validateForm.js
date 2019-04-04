/**
 * Validate form.
 */
function validate() {
    if ($('#description').val() === '') {
        alert($('#name').attr('title'));
        return false;
    }
    if ($('#dueDate').val() === '') {
        alert($('#login').attr('title'));
        return false;
    }
    return true;
}
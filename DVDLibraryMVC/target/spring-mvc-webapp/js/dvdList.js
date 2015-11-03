// Document ready function
$(document).ready(function () {
    loadDvds();

    // NEW CODE START
// on click for our add button
    $('#add-button').click(function (event) {
// we don’t want the button to actually submit
// we'll handle data submission via ajax
        event.preventDefault();
// Make an Ajax call to the server. HTTP verb = POST, URL = contact
        $.ajax({
            type: 'POST',
            url: 'dvd',
// Build a JSON object from the data in the form
            data: JSON.stringify({
                title: $('#add-title').val(),
                releaseDate: $('#add-release-date').val(),
                director: $('#add-director').val(),
                studio: $('#add-studio').val(),
                comments: $('#add-comments').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
// If the call succeeds, clear the form and reload the summary table
            $('#add-title').val('');
            $('#add-release-date').val('');
            $('#add-director').val('');
            $('#add-studio').val('');
            $('#add-comments').val('');
            $('#validationErrors').empty();
            loadDvds();
//return false;
        }).error(function (data, status) {
            $('#validationErrors').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                $('#validationErrors').append(validationError.message).append($('<br>'));
            });
        });
    });

// onclick handler for edit button
    $('#edit-button').click(function (event) {
// prevent the button press from submitting the whole page

// Ajax call -
// Method - PUT
// URL - contact/{id}
// Just reload all of the Contacts upon success
        $.ajax({
            type: 'PUT',
            url: 'dvd/' + $('#edit-dvd-id').val(),
            data: JSON.stringify({
                dvdId: $('#edit-dvd-id').val(),
                title: $('#edit-title').val(),
                releaseDate: $('#edit-release-date').val(),
                director: $('#edit-director').val(),
                studio: $('#edit-studio').val(),
                comments: $('#edit-comments').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            loadDvds();
            
            $('#editModal').modal('hide');
        }).error(function(data, status) {
        $('validationErrorEdit').empty();
        $.each(data.responseJSON.fieldErrors, function(index, validationError) {
          
        $('#validationErrorEdit').append(validationError.message).append($('<br>')); 
    });

    });
}); 



    $('#search-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'Find/dvds',
            data: JSON.stringify({
                title: $('#search-title').val(),
                releaseDate: $('#search-release-date').val(),
                director: $('#search-director').val(),
                studio: $('#search-studio').val(),
                comments: $('#search-comments').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-title').val('');
            $('#search-release-date').val('');
            $('#search-director').val('');
            $('#search-studio').val('');
            $('#search-comments').val('');
            fillDvdTable(data, status);
        });
    });
});
//==========
// FUNCTIONS
//==========
// Load contacts into the summary table
function loadDvds() {
    $.ajax({
        url: "dvds"
    }).success(function (data, status) {
        fillDvdTable(data, status);
    });
}
function fillDvdTable(dvdList, status) {
    // clear the previous list
    clearDvdTable();
// grab the tbody element that will hold the new list of contacts
    var cTable = $('#contentRows');
// Make an Ajax GET call to the 'contacts' endpoint. Iterate through
// each of the JSON objects that are returned and render them to the
// summary table.
    $.each(dvdList, function (index, dvd) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.dvdId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(dvd.title)
                                ) // ends the <a> tag
                        ) // ends the <td> tag for the contact name

                .append($('<td>').text(dvd.releaseDate)) //*************************
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.dvdId,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>').append($('<a>')
                                .attr({
                                    'onClick': 'deleteDvd(' +
                                            dvd.dvdId + ')'
                                })
                                .text('Delete')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Delete
                ); // ends the <tr> for this Contact
    }); // ends the 'each' function

}



// Clear all content rows from the summary table
function clearDvdTable() {
    $('#contentRows').empty();
}
// This code runs in response to the show.bs.modal event - it gets the correct
// contact data and renders it to the dialog
$('#detailsModal').on('show.bs.modal', function (event) {
// Get the element that triggered this event - in our case it is a contact
// name link in the summary table. This link has an attribute that contains
// the contactId for the given contact. We'll use that to retrieve the
// contact's details.
    var element = $(event.relatedTarget);
// grab the contact id
    var dvdId = element.data('dvd-id');
// PLACEHOLDER: Eventually we'll make an ajax call to the server to get the
//details for this contact but for now we'll load the dummy
// data
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.dvdId);
        modal.find('#dvd-title').text(dvd.title);
        modal.find('#dvd-releaseDate').text(dvd.releaseDate);
        modal.find('#dvd-director').text(dvd.director);
        modal.find('#dvd-studio').text(dvd.studio);
        modal.find('#dvd-comments').text(dvd.comments);
    });
});

// This code runs in response to the show.bs.modal event - it gets the correct
// contact data and renders it to the dialog
$('#editModal').on('show.bs.modal', function (event) {
// Get the element that triggered this event - in our case it is a contact
// name link in the summary table. This link has an attribute that contains
// the contactId for the given contact. We'll use that to retrieve the
// contact's details.
    var element = $(event.relatedTarget);
// Grab the contact id
    var dvdId = element.data('dvd-id');
// PLACEHOLDER: Eventually we'll make an ajax call to the server to get the
//details for this contact but for now we'll load the dummy
//data
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {

        modal.find('#dvd-id').text(dvd.dvdId);
        modal.find('#edit-dvd-id').val(dvd.dvdId);
        modal.find('#edit-title').val(dvd.title);
        modal.find('#edit-release-date').val(dvd.releaseDate);
        modal.find('#edit-director').val(dvd.director);
        modal.find('#edit-studio').val(dvd.studio);
        modal.find('#edit-comments').val(dvd.comments);
    });

});



function deleteDvd(id) {
    var answer = confirm("Do you really want to delete this Dvd?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + id
        }).success(function () {
            loadDvds();
        });
    }
}
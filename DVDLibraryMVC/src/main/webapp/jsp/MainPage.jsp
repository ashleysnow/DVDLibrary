<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/Home">Home</a></li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/MainPage">Add</a></li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/Find">Find</a></li>   
                </ul>
            </div>
            <div class="row">    
                <div class="col-md-6">
                    <h2>My DVDs</h2>
                    <table id="dvdTable" class="table table-hover">
                        <tr>
                            <th width="40%">Title</th>
                            <th width="30%">Release Date</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr> 
                        <tbody id="contentRows"></tbody>
                    </table>
                </div> <!-- End col div -->   
                <div class="col-md-6">
                    <h2>Add New DVD</h2>
                    <form class="form-horizontal" role="form">

                        <div class="form-group">
                            <label for="add-title" class="col-md-4 control-label">
                                Title:
                            </label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-title"
                                       placeholder="Title"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-release-date" class="col-md-4 control-label">
                                Release Date:
                            </label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-release-date"
                                       placeholder="Release Date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-director" class="col-md-4 control-label">
                                Director:
                            </label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-director"
                                       placeholder="Director"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-studio" class="col-md-4 control-label">
                                Studio:
                            </label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-studio"
                                       placeholder="Studio"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-comments" class="col-md-4 control-label">
                                Comments:
                            </label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-comments"
                                       placeholder="Comments"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit"
                                        id="add-button"
                                        class="btn btn-default">
                                    Create Dvd
                                </button>
                            </div>
                        </div>
                    </form>
                    <div id="validationErrors" style="color: red"/>
                </div> <!-- End col div -->
            </div> <!-- End row div -->
        </div>
        <!---- </div>  not sure about this div, was in my code but not teachers
        <!-- Placed at the end of the document so the pages load faster -->
        <!-- Details Modal -->
        <!--
        The aria- attributes are for Accessible Rich Internet Applications
        standards -
        their purpose is to make web applications more accessible to people with
        disabilities.
        -->
        <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">DVD Details</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="dvd-id"></h3>
                        <table class="table table-bordered">
                            <tr>
                                <th>Title:</th>
                                <td id="dvd-title"></td>
                            </tr>
                            <tr>
                                <th>Release Date:</th>
                                <td id="dvd-releaseDate"></td>
                            </tr>
                            <tr>
                                <th>Director:</th>
                                <td id="dvd-director"></td>
                            </tr>
                            <tr>
                                <th>Studio:</th>
                                <td id="dvd-studio"></td>
                            </tr>
                            <tr>
                                <th>Comments:</th>
                                <td id="dvd-comments"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            Close
                        </button>
                    </div>
                </div>
            </div>
        </div>   
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="detailsModalLabel">Edit DVD</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="dvd-id"></h3>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="edit-title" class="col-md-4 control-label">
                                    Title:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-title" placeholder="Title">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-release-date" class="col-md-4 control-label">
                                    Release Date:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-release-date"placeholder="Release Date">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-director" class="col-md-4 control-label">
                                    Director:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-director" placeholder="Director">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-studio" class="col-md-4 control-label">
                                    Studio:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-studio" placeholder="Studio">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-comments" class="col-md-4 control-label">
                                    Comments:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-comments" placeholder="Comments">
                                </div>
                            </div>   
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit" id="edit-button" class="btn btn-default" data-dismiss="modal">
                                        Edit Dvd
                                    </button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                        Cancel
                                    </button>
                                    <input type="hidden" id="edit-dvd-id">
                                </div>
                            </div>
                        </form>
                        <div id="validationErrors" style="color: red"/>
                    </div>
                </div>
            </div>
        </div>  
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/dvdList.js"></script>
</body>
</html>
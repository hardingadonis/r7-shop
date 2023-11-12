<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
        <link rel="stylesheet" href="./assets/css/style.css"/>

        <link rel="icon" type="image/x-icon" href="./assets/imgs/favicon/favicon.ico">

        <title>R7 Shop | Home</title>
    </head>

    <body>
        <%@include file="common/_header.jsp" %>

        <div id="carousel_home" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000" data-bs-wrap="true">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="./assets/imgs/banners/8ad13d8367f83c99e0bfc8d59354c91b122ea510238ebca37526847f773a917a.jpg" class="d-block w-100">
                </div>
                <div class="carousel-item">
                    <img src="./assets/imgs/banners/042323b1c6d13f269cad2afa42cfa174cbcb7f0a88b786c3cae7a52f696f45dd.jpg" class="d-block w-100">
                </div>
                <div class="carousel-item">
                    <img src="./assets/imgs/banners/8539505b94c12959a1847de8e0051b0d6233074586b500ba643d10d978f4fec1.jpg" class="d-block w-100">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carousel_home" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carousel_home" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

        <div class="container position-relative text-center">
            <h2 class="display-6 py-5">
                Most Popular
            </h2>

            <a href="#" class="btn btn-outline-dark my-5">View All Products</a>
        </div>

        <%@include file="common/_footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

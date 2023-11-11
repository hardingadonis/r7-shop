<nav class="navbar navbar-expand-lg navbar-light bg-light position-fixed top-0 start-0 w-100">
    <div class="container">
        <a href="#" class="navbar-brand d-lg-none text-uppercase">
            R7 SHOP
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse p-2 flex-column" id="navbarContent">
            <div class="d-flex justify-content-center justify-content-lg-between flex-column flex-lg-row w-100">
                <form class="d-flex">
                    <input type="search" class="form-control me-2" placeholder="Search" />
                    <button class="btn btn-outline-dark" type="submit">
                        <i class="fas fa-search"></i>
                    </button>
                </form>
                <a href="#" class="navbar-brand d-none d-lg-block text-uppercase">R7 SHOP</a>
                <ul class="navbar-nav">
                    <c:if test="${sessionScope.user != null}">
                        <li class="nav-item d-flex align-items-center dropdown">
                            <a href="#" class="nav-link mx-2 nav-link dropdown-toggle" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                <img src="./${sessionScope.user.avatarPath}" alt="Avatar" width="20"
                                     height="20">
                                ${sessionScope.user.fullName}
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a class="dropdown-item" href="#">Settings</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">Purchase History</a>
                                </li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">Logout</a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item d-flex align-items-center">
                            <a href="#" class="nav-link mx-2">
                                <i class="fas fa-shopping-cart"></i>
                                Cart
                            </a>
                            <span class="badge rounded-pill bg-secondary">0</span>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.user == null}">
                        <li class="nav-item d-flex align-items-center">
                            <button class="btn btn-light">
                                <i class="fas fa-plus"></i>
                                Register
                            </button>
                        </li>
                        <li class="nav-item d-flex align-items-center">
                            <button class="btn btn-light">
                                <i class="fas fa-sign-in"></i>
                                Login
                            </button>
                        </li>
                    </c:if>
                </ul>
            </div>
            <div class="d-block w-100">
                <ul class="navbar-nav d-flex justify-content-center align-items-center pt-3">
                    <li class="nav-item mx-2">
                        <a href="#" class="nav-link">
                            Modern 14 series
                        </a>
                    </li>
                    <li class="nav-item mx-2">
                        <a href="#" class="nav-link">
                            Modern 15 series
                        </a>
                    </li>
                    <li class="nav-item mx-2">
                        <a href="#" class="nav-link">
                            GF series
                        </a>
                    </li>
                    <li class="nav-item mx-2">
                        <a href="#" class="nav-link">
                            GS series
                        </a>
                    </li>
                    <li class="nav-item mx-2">
                        <a href="#" class="nav-link">
                            Bravo series
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>
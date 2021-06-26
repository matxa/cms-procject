"""CMS frontend - consuming from cms-api"""
from re import U
import re
from flask import Flask, request, jsonify
from flask import render_template, redirect, url_for
from flask.helpers import flash
from werkzeug.datastructures import auth_property
from werkzeug.local import F
from forms import LoginForm, RegisterForm, ArticleForm
import requests
from flask_login import (
    LoginManager, UserMixin, login_user,
    login_required, logout_user, current_user)


"""Server Configurations"""
app = Flask(__name__)
app.secret_key = "BFD5F3250E2A6FF940C728D351AEDB64DB257B2B"
API_URL = "http://localhost:8080"

"""USER LOGIN"""
login_manager = LoginManager()
login_manager.init_app(app)
login_manager.login_view = 'login'
login_manager.login_message = "Please Login!"
login_manager.login_message_category = "flash-error"


class User(UserMixin):
    """User Model"""
    id = ''


@login_manager.user_loader
def load_user(user_id):
    user = User()
    user.id = user_id
    return user


@app.route("/home", methods=["GET", "POST"], strict_slashes=False)
def home():
    """Home"""
    form = ArticleForm()

    if not current_user.is_authenticated:
        return redirect(url_for("login"))

    author = None
    req = requests.get(f"{API_URL}/authors/get/{current_user.id}")
    if req.status_code == 200:
        author = req.json()["author"]

    articles = []
    req = requests.get(f"{API_URL}/articles/author/{current_user.id}")
    if req.status_code == 200:
        articles = req.json()["articles"]

    if request.method == 'POST':
        form.email = current_user.id
        req = requests.post(
            f"{API_URL}/articles/article", json=form.get_object())
        if req.status_code == 201:
            return redirect(url_for("home"))
        else:
            return redirect(url_for("home"))

    return render_template(
        "home.html", author=author, form=form, articles=articles)


@app.route("/", methods=["GET", "POST"], strict_slashes=False)
def login():
    """Login"""
    if current_user.is_authenticated:
        return redirect(url_for("home"))

    form = LoginForm()
    authors = []
    authors_amount = 0

    # Get all the registered authors
    try:
        req = requests.get(f"{API_URL}/authors")
        if req.status_code == 200:
            authors = req.json()["authors"]
            authors_amount = req.json()["amount"]
    except requests.exceptions.ConnectionError:
        pass

    # Login
    if request.method == 'POST':
        req = requests.get(f"{API_URL}/authors/get/{form.get_email()}")
        if req.status_code == 200:
            user = User()
            user.id = req.json()["author"]["email"]
            login_user(user)
            return redirect(url_for("home"))
        else:
            flash("Author doesn't exist!", category='flash-error')
            return redirect(url_for("login"))

    return render_template(
        "login.html", form=form, authors=authors,
        authors_amount=authors_amount)


@app.route(
    "/register", strict_slashes=False, methods=["GET", "POST"])
def register():
    """Register"""
    if current_user.is_authenticated:
        return redirect(url_for("home"))
    form = RegisterForm()

    if request.method == 'POST':
        req = requests.post(
            f"{API_URL}/authors/author", json=form.get_object())
        print(req.status_code)
        if req.status_code == 201:
            flash(req.json()["message"], category='flash-success')
            return redirect(url_for("login"))
        elif req.status_code == 400:
            flash(req.json()["error"], category='flash-error')
            return redirect(url_for("register"))
        else:
            flash("Something went wrong!", category='flash-error')
            return redirect(url_for("register"))

    return render_template(
        "register.html", form=form)


@app.route('/logout', strict_slashes=False)
def logout():
    """Logout user"""
    if not current_user.is_authenticated:
        return redirect(url_for("login"))
    logout_user()
    flash("Logout successfully!", category='flash-success')
    return redirect(url_for("login"))


@app.route('/delete_author', strict_slashes=False)
def delete_author():
    """Delete author"""
    if not current_user.is_authenticated:
        return redirect(url_for("login"))

    req = requests.delete(f"{API_URL}/authors/delete/{current_user.id}")
    if req.status_code == 202:
        flash("Account deleted successfully!", category='flash-success')
        return redirect(url_for("logout"))
    else:
        return redirect(url_for("home"))


@app.route('/delete_article/<id>', strict_slashes=False)
def delete_article(id):
    """Delete Article"""
    if not current_user.is_authenticated:
        return redirect(url_for("login"))
    req = requests.delete(f"{API_URL}/articles/article/delete/{id}")
    return redirect(url_for("home"))


@app.route('/article/<id>', strict_slashes=False)
def article(id):
    """See Article"""
    article = None
    if not current_user.is_authenticated:
        return redirect(url_for("login"))
    req = requests.get(f"{API_URL}/articles/article/get/{id}")
    if req.status_code == 200:
        article = req.json()['article']

    return render_template("article.html", article=article)


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000,  debug=True)

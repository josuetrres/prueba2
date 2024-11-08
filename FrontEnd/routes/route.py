from flask import Blueprint, abort, request, render_template, redirect,url_for, flash
import requests
router = Blueprint('router', __name__)

@router.route("/registroList")
def RegistroList():
    r = requests.get('http://localhost:8080/myapp/registro/all')
    data =  r.json()
    return render_template('RegistroList.html', lista = data["data"])
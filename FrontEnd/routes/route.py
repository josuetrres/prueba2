from flask import Blueprint, abort, request, render_template, redirect,url_for, flash
import requests
router = Blueprint('router', __name__)

@router.route('/')
def home():
    return render_template('home.html')

@router.route("/registroList")
def registro_list():
    r = requests.get('http://localhost:8080/myapp/registro/all')
    data =  r.json()
    return render_template('RegistroList.html', lista = data["data"])


@router.route('/saveRegistro', methods=['GET', 'POST'])
def save_registro():
    if request.method == 'POST':
        registro_html_data = {
            "id": request.form['id'], 
            "codigo": request.form['codigo'],
            "esCorrecto": request.form.get('esCorrecto') == 'on'
        }
        
        response = requests.post('http://localhost:8080/myapp/registro/save', json=registro_html_data)
        if response.status_code == 200:
            return redirect(url_for('router.home')) 
        else:
            return render_template('saveRegistroHtml.html', error=response.json()["data"])
    
    return render_template('saveRegistro.html')



@router.route('/validarRegistro/<int:id>', methods=['POST'])
def validar_registro(id):
    data = {
        'id': id
    }

    response = requests.post('http://localhost:8080/myapp/registro/validate', json=data)

    if response.status_code == 200:
        flash('Código validado correctamente', 'success')
    else:
        flash('Error al validar el código', 'danger')

    return redirect(url_for('router.registro_list'))

'use strict';

// usersTable
const tbody = $('#tBodyAdmin');
getTableUser()

function getTableUser() {

    tbody.empty()

    fetch("http://localhost:8080/api/users")

        .then(res => res.json())
        .then(js => {
            js.forEach(item => {
                const users = `$(
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.firstname}</td>
                        <td>${item.lastname}</td>
                        <td>${item.age}</td>
                        <td>${item.email}</td>
                        <td>${item.roles.map((e)=>e.name)}</td>
                        <td>
                            <button type="button" class="btn btn-info btn-sm" data-toggle="modal"
                            data-target="#edit" onclick="editModal(${item.id})">
                            Edit
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal"
                            data-target="#delete" onclick="deleteModal(${item.id})">
                                Delete
                            </button>
                        </td>
                    </tr>)`;
                tbody.append(users)
            })
        })
}

// modal
async function getOneUser(id) {
    let url = "http://localhost:8080/api/users/" + id;
    let response = await fetch(url);
    return await response.json();
}

async function openAndFillInTheEditModal(form, modal, id) {
    modal.show();
    let user = await getOneUser(id) || '';
    form.idEdit.value = user.id || '';
    form.firstnameEdit.value = user.firstname || '';
    form.lastnameEdit.value = user.lastname || '';
    form.ageEdit.value = user.age || '';
    form.emailEdit.value = user.email || '';
    form.passwordEdit.value = user.password || '';
    form.rolesEdit.value = user.roles.map(role => role.name) ;

    let roleCurrent = user.roles.map(role => role.name);

    $("#rolesDelete option").filter(function() {
        return roleCurrent.indexOf($(this).text()) !== -1;
    }).prop('selected', true);
}

async function openAndFillInTheDeleteModal(form, modal, id) {
    modal.show();
    let user = await getOneUser(id);
    form.idDelete.value = user.id;
    form.firstnameDelete.value = user.firstname;
    form.lastnameDelete.value = user.lastname;
    form.ageDelete.value = user.age;
    form.emailDelete.value = user.email;
    form.rolesDelete.value = user.roles;

    let roleCurrent = user.roles.map(role => role.name);

    $("#rolesDelete option").filter(function() {
        return roleCurrent.indexOf($(this).text()) !== -1;
    }).prop('selected', true);
}
//editModal
let formEdit = document.forms["formEdit"];
editUser();

async function editModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#edit'));
    await openAndFillInTheEditModal(formEdit, modal, id);
}

function editUser() {
    formEdit.addEventListener("submit", event => {
        event.preventDefault();

        let roles = $("#rolesEdit").val()

        for (let i = 0; i < roles.length; i++) {
            if (roles[i] === 'ROLE_ADMIN') {
                roles[i] = {
                    'id': 1,
                    'role': 'ROLE_ADMIN',
                    "authority": "ROLE_ADMIN"
                }
            }
            if (roles[i] === 'ROLE_USER') {
                roles[i] = {
                    'id': 2,
                    'role': 'ROLE_USER',
                    "authority": "ROLE_USER"
                }
            }
        }

        fetch("http://localhost:8080/api/users/" + formEdit.idEdit.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.idEdit.value,
                firstname: formEdit.firstnameEdit.value,
                lastname: formEdit.lastnameEdit.value,
                age: formEdit.ageEdit.value,
                email: formEdit.emailEdit.value,
                password: formEdit.passwordEdit.value,
                roles: roles

            })
        }).then(() => {
            $('#closeEdit').click();
            getTableUser()
        });
    });
}

//deleteModal
let deleteForm = document.forms["formDelete"]
deleteUser()

async function deleteModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#delete'));
    await openAndFillInTheDeleteModal(deleteForm, modal, id);
}

function deleteUser() {
    deleteForm.addEventListener("submit", event => {
        event.preventDefault();
        fetch("http://localhost:8080/api/users/" + deleteForm.idDelete.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#closeDelete').click();
            getTableUser();
        });
    });
}

//NewUser
let form = document.forms["new"];
createNewUser()

function createNewUser() {
    form.addEventListener("submit", event => {
        event.preventDefault();
        let roles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) roles.push({
                id: form.roles.options[i].value,
                role: "ROLE_" + form.roles.options[i].text
            });
        }

        fetch("http://localhost:8080/api/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstname: form.firstnameNew.value,
                lastname: form.lastnameNew.value,
                age: form.ageNew.value,
                email: form.emailNew.value,
                password: form.passwordNew.value,
                roles: roles
            })
        }).then(() => {
            form.reset();
            $('#users_table-tab').click();
            getTableUser()
        });
    });
}
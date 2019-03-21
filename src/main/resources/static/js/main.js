(function() {

    "use strict";

    let editBtns = document.getElementsByClassName('edit-btn');

    function buildUpdateFormRow(rowData) {
        let html = `
                <tr>
                    <form id="update-form-${rowData.id}" action="/contacts/${rowData.id}/update" method="POST">
                `;
        for (let key in rowData) {
            if (key !== 'id') {
                html += `
                        <td class="hide-before" data-prop="${key}">
                            <input type="text" name="${key}" form="update-form-${rowData.id}" class="form-input" id="${key}" value="${rowData[key]}"/>
                        </td>
                       `;
            }
        }
        html += `
                    </form>
                    <td>
                        <button class="save-btn" id="save-btn-${rowData.id}">Save</button>
                        <button class="cancel-btn" id="cancel-btn-${rowData.id}">Cancel</button>
                        <form class="delete-form" id="delete-form-${rowData.id}" action="/contacts/${rowData.id}/delete" method="POST">
                            <button class="delete-btn" id="delete-btn-${rowData.id}">Delete</button>
                        </form>
                    </td>
                </tr>`;
        return html;
    }

    function buildNewFormRow(fields) {
        let html = `<form id="create-form" action="/contacts/create" method="POST">`;
        for (let field of fields) {
            html += `
                    <td data-prop="${field}">
                        <input form="create-form" type="text"  class="form-input" id="${field}" name="${field}" placeholder="Enter ${field}"/>
                    </td>
                   `;
        }
        html += `
                    <td>
                        <button class="save-create-btn" id="save-create-btn">Save</button>
                        <button class="cancel-create-btn" id="cancel-create-btn">Cancel</button>
                    </td>`;
        return html;
    }

    function returnRowdata(e) {
        let row = e.target.parentNode.parentNode.children;
        let rowData = {};
        for (let cell of row) {
            let prop = cell.getAttribute("data-prop");
            rowData[prop] = (prop === 'id') ? cell.childNodes[0].getAttribute("data-dbid") : cell.innerHTML;
        }
        return rowData;
    }

    const createRowBtnListeners = (id, oldRowHtml) => {
        return {
            save: function(e) {
                e.target.parentNode.parentNode.firstElementChild.submit();
            },
            cancel: function(e) {
                e.preventDefault();
                for (let editBtn of document.querySelectorAll('.edit-btn')) {
                    editBtn.classList.remove("hide");
                }
                e.target.parentNode.parentNode.innerHTML = oldRowHtml;
                document.querySelector(`button[data-dbid='${id}']`).addEventListener("click", editBtnHandler);

                document.querySelector(`button[data-dbid='${id}']`).addEventListener("click", editBtnHandler);

                for (let td of document.querySelector(`button[data-dbid='${id}']`).parentElement.parentElement.children) {
                    td.addEventListener("dblclick", clickRowEditBtn);
                }
                document.querySelector("#add-btn").classList.remove("hide");
            },
            delete: function(e) {
                e.preventDefault();
                if (confirm("Are you sure you wish to delete this contact?")) {
                    e.target.parentElement.submit();
                }
            }
        };
    };

    function addRowButtonListeners(id, createRowBtnListeners, oldRowHtml) {
        const rowBtnListeners = createRowBtnListeners(id, oldRowHtml);
        for (let key in rowBtnListeners) {
            document.querySelector(`#${key}-btn-${id}`).addEventListener("click", rowBtnListeners[key]);
        }
    }

    function editBtnHandler(e) {
        if (document.querySelector("input") !== null) {
            return false;
        }
        let rowData = returnRowdata(e);
        let formHtml = buildUpdateFormRow(rowData);
        let oldRowHtml = e.target.parentNode.parentNode.innerHTML;
        e.target.parentNode.parentNode.innerHTML = formHtml;
        addRowButtonListeners(rowData.id, createRowBtnListeners, oldRowHtml);
        for (let editBtn of document.querySelectorAll('.edit-btn')) {
            editBtn.classList.add("hide");
        }
        document.querySelector("#add-btn").classList.add("hide");
    }

    function clickRowEditBtn(e) {
        e.target.parentElement.lastElementChild.firstElementChild.click();
    }

    for (let td of document.querySelectorAll('td')) {
        td.addEventListener("dblclick", clickRowEditBtn);
    }

    for (let editBtn of editBtns) {
        editBtn.addEventListener("click", editBtnHandler);
    }

    document.querySelector('#add-btn').addEventListener("click", function() {
        let node = document.createElement("TR");
        node.innerHTML = buildNewFormRow(['firstName', 'lastName', 'middleInitial', 'suffix', 'phoneNumber', 'email', 'address']) + "</form>";
        document.querySelector("tbody").appendChild(node);
        document.querySelector("#add-btn").classList.add("hide");
        document.querySelectorAll(".edit-btn").forEach(function(node) {
            node.classList.add("hide");
        });
        document.querySelector("#add-btn").classList.add("hide");
        document.querySelector("#cancel-create-btn").addEventListener("click", function(e) {
            e.target.parentNode.parentNode.remove();
            document.querySelectorAll("#add-btn, .edit-btn").forEach(function(element) {
                element.classList.remove("hide");
            })
        });

        document.querySelector("#save-create-btn").addEventListener("click", function(e) {
            document.getElementById("create-form").submit();
        });
    });
})();

function login(){

    let mobile = document.getElementById("mobile").value;
    let password = document.getElementById("password").value;
    var token;

    fetch('login', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            mobile: mobile,
            password: password
        })
    }).then(response => response.text()).then(res => {
        if (res == "Admin") {
            window.location = "/crudapp/admin-panel";
        } else if (res == "HR-Manager") {
            window.location = "/crudapp/hr-panel";
        }
    });
}

function goToEditEmployee(empId){
    window.location = "/crudapp/employee-controller/edit-employee/?id="+empId;
}

function deleteEmployee(empId) {
    fetch('employee-controller', {
        method: 'delete',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id: empId,
        })
    }).then(response => response.text()).then(res => {
        if (res === "Employee Deleted Successful!"){
            window.location = "/crudapp/admin-panel";
        }
    });
}

function goRegisterEmployee(){
    window.location = "/crudapp/employee-controller";
}

function registerEmployee(){
   let employee_name =  document.getElementById("emp_name").value;
   let employee_salary =  document.getElementById("emp_salary").value;
   let employee_department =  document.getElementById("department_id").value;
   let employee_position =  document.getElementById("position_id").value;

    fetch('employee-controller', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name:employee_name,
            salary:employee_salary,
            department:employee_department,
            position:employee_position,
        })
    }).then(response => response.text()).then(res => {

        if (res === "Enter Employee Name"){
            alert("Enter Employee Name");
        }else if (res === "Enter Employee Salary"){
            alert("Enter Employee Salary");
        }else{
            alert("Employee Registered Successful!");
        }


    });
}


// Search Employees

var table = document.getElementById("data_table")

function debounce(func, timeout = 1000) {

    let timer;

    return (...args) => {
        clearTimeout(timer);
        timer = setTimeout(() => {
            func.apply(this, args);
        }, timeout);


        // remove DOM Saved table data
        var rowCount = table.rows.length;
        for (var i = rowCount - 1; i > 0; i--) {
            table.deleteRow(i);
        }

    };
}


function saveInput() {

    let name = document.getElementById("search_input").value;

    fetch('admin-panel', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name: name
        })
    }).then(response => response.json()).then(res => {
        if (document.getElementById("search_input").value == "") {
            data = "";
            function createTableFromObjects(data) {

                table.style.border = "1px solid #000";
                table.style.td = "1px solid #000";

                // Create table header row
                const keys = Object.keys(data[0]);
                keys.push("delete", "update")

                // Create table data rows
                for (var i = 0; i < data.length; i++) {

                    let deleteBtn = document.createElement('button');
                    deleteBtn.innerText = "delete"
                    deleteBtn.setAttribute("id", "dbtn");
                    deleteBtn.setAttribute("onclick", `deleteEmployee(${data[i]["id"]})`)

                    let editBtn = document.createElement('button');
                    editBtn.innerText = "edit"
                    editBtn.setAttribute("id", "ebtn");

                    const dataRow = document.createElement('tr');

                    for (const key of keys) {

                        const dataCell = document.createElement('td');

                        dataCell.style.border = "1px solid #000";

                        dataCell.textContent = data[i][key];

                        dataRow.appendChild(dataCell);
                    }

                    dataRow.insertCell(6).appendChild(deleteBtn);
                    dataRow.insertCell(7).appendChild(editBtn);
                    dataRow.deleteCell(9);
                    dataRow.deleteCell(8);
                    table.appendChild(dataRow);
                }

                return table;
            }

            // document.getElementById('table-container').firstElementChild.remove();

            const table1 = createTableFromObjects(res);

            tableContainer = document.getElementById('table-container');
            tableContainer.appendChild(table1);

        } else {
            function createTableFromObjects(data) {

                table.style.border = "1px solid #000";
                table.style.td = "1px solid #000";

                // Create table header row
                const keys = Object.keys(data[0]);
                keys.push("delete", "update")

                // Create table data rows
                for (var i = 0; i < data.length; i++) {

                    let deleteBtn = document.createElement('button');
                    deleteBtn.innerText = "delete"
                    deleteBtn.setAttribute("id", "dbtn");
                    deleteBtn.setAttribute("onclick", `deleteEmployee(${data[i]["id"]})`)

                    let editBtn = document.createElement('button');
                    editBtn.innerText = "edit"
                    editBtn.setAttribute("id", "ebtn");
                    editBtn.setAttribute("onclick", `goToEditEmployee(${data[i]["id"]})`)


                    const dataRow = document.createElement('tr');

                    for (const key of keys) {

                        const dataCell = document.createElement('td');

                        dataCell.style.border = "1px solid #000";

                        dataCell.textContent = data[i][key];

                        dataRow.appendChild(dataCell);
                    }

                    dataRow.insertCell(6).appendChild(deleteBtn);
                    dataRow.insertCell(7).appendChild(editBtn);
                    dataRow.deleteCell(9);
                    dataRow.deleteCell(8);
                    table.appendChild(dataRow);
                }

                return table;
            }

            // document.getElementById('table-container').firstElementChild.remove();

            const table1 = createTableFromObjects(res);

            tableContainer = document.getElementById('table-container');
            tableContainer.appendChild(table1);
        }
    })

}

const processChange = debounce(() => saveInput());
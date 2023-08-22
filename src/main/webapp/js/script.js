document.getElementById("btn").addEventListener("click", () => {

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
})

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
        if (res == "Employee Deleted Successful!"){
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

        if (res == "Enter Employee Name"){
            alert("Enter Employee Name");
        }else if (res == "Enter Employee Salary"){
            alert("Enter Employee Salary");
        }else{
            alert("Employee Registered Successful!");
        }


    });
}



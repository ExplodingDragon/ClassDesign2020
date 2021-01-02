import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../services/http.service';

@Component({
  selector: 'app-admin-user-page',
  templateUrl: './admin-user-page.component.html',
  styleUrls: ['./admin-user-page.component.scss']
})
export class AdminUserPageComponent implements OnInit {

  constructor(private http: HttpService) {
  }

  data = [];

  dialog = {
    show: false,
    title: 'title',
    content: [{
      title: '用户ID',
      value: '',
      type: 'number',
      editable: false
    }, {
      title: '用户名',
      value: '',
      type: 'text',
      editable: true
    }, {
      title: '邮箱',
      value: '',
      type: 'email',
      editable: true
    }, {
      title: '性别',
      value: '',
      type: 'text',
      editable: true
    }, {
      title: '管理员',
      value: '',
      type: 'text',
      editable: true
    }, {
      title: '可登录',
      value: '',
      type: 'text',
      editable: true
    }, {
      title: '密码',
      value: '',
      type: 'text',
      editable: true
    }], saveHook: (data) => {
    }
  };


  updateItem() {
    this.http.get<any>('/api/users?pageIndex=0&pageSize=100', (data) => {
        this.data = data;
      }
    );
  }

  ngOnInit(): void {
    this.updateItem();
  }

  editUser(item: any) {
    this.dialog.title = '修改用户：' + item.name;
    this.dialog.saveHook = (data) => {
      data = {
        id: data[0].value,
        email: data[2].value,
        sex: data[3].value,
        name: data[1].value,
        image: '',
        password: '',
        admin: data[4].value,
        regiserDate: data[5].value,
        canLogin: true
      };
      this.http.put<any>('/api/users/' + data.id, data, (res) => {
        alert('修改成功！');
        this.updateItem();
      });
    };
    this.dialog.content[0].value = item.id;
    this.dialog.content[1].value = item.name;
    this.dialog.content[2].value = item.email;
    this.dialog.content[3].value = item.sex;
    this.dialog.content[4].value = item.admin;
    this.dialog.content[5].value = item.canLogin;
    this.dialog.show = true;

  }

  deleteUser(item: any): void {

    if (confirm('确定删除用户\'' + item.name + '\'  吗  ？')) {
      this.http.delete('/api/users/' + item.id, (data) => {
        if (data.status) {
          this.updateItem();
        }
      });
    }
  }

  create() {
    this.dialog.title = '创建用户：';
    this.dialog.saveHook = (data) => {
      data = {
        id: data[0].value,
        email: data[2].value,
        sex: data[3].value,
        name: data[1].value,
        image: '',
        password: data[6].value,
        admin: data[4].value,
        regiserDate: data[5].value,
        canLogin: true
      };
      this.http.post<any>('/api/users/' + data.id, data, (res) => {
        alert('创建成功！');
        this.updateItem();
      });
    };
    this.dialog.content[0].value = '';
    this.dialog.content[1].value = '';
    this.dialog.content[2].value = '';
    this.dialog.content[3].value = '';
    this.dialog.content[4].value = '';
    this.dialog.content[5].value = '';
    this.dialog.content[6].value = '';
    this.dialog.show = true;

  }
}

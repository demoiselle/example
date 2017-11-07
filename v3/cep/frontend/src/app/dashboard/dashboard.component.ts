import { Component, OnInit, ViewChild } from "@angular/core";
import { ModalDirective } from "ngx-bootstrap";

import { NotificationService } from "../shared/";
import { DashboardService } from "./dashboard.service";
import { Dashboard } from "./dashboard.model";

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html"
})
export class DashboardComponent implements OnInit {
  dashboard: Dashboard;
  dashboards: Dashboard[];

  constructor(
    private service: DashboardService,
    private notificationService: NotificationService
  ) {
    this.dashboard = new Dashboard();
  }

  ngOnInit() {}

  get() {
    this.service.get(this.dashboard.cep).subscribe(
      result => {
        try {
          this.dashboard = result[0];
        } catch (e) {
          console.log("Can not convert result to JSON.");
          console.log(e);
          this.dashboards = [];
        }
      },
      error => {
        this.notificationService.error(
          "Não foi possível carregar a lista de itens!"
        );
        this.dashboards = error;
      }
    );
  }
}

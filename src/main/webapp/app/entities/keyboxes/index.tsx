import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Keyboxes from './keyboxes';
import KeyboxesDetail from './keyboxes-detail';
import KeyboxesUpdate from './keyboxes-update';
import KeyboxesDeleteDialog from './keyboxes-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={KeyboxesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={KeyboxesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={KeyboxesDetail} />
      <ErrorBoundaryRoute path={match.url} component={Keyboxes} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={KeyboxesDeleteDialog} />
  </>
);

export default Routes;
